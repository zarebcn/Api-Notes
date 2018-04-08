package com.zarebcn.notas.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zarebcn.notas.model.Note;
import com.zarebcn.notas.service.NotesService;
import com.zarebcn.notas.util.HandlebarsUtil;

@Path("/notes")
@Produces(MediaType.TEXT_HTML)
public class NotesControllerNew {
	
	NotesService notesService = new NotesService();
	
	public NotesControllerNew (NotesService notesService) {
		this.notesService = notesService;
	}
	
	@GET
	public String viewNotes() {
		
		Collection<Note> notes = notesService.viewNotes();
		
		Map<String, Object> handlebarsValues = new HashMap<>();
	        
	    handlebarsValues.put("pageTitle", "Notes");
	    handlebarsValues.put("notes", notes);

	    return HandlebarsUtil.processTemplate("notes", handlebarsValues);
	}
	
	@GET
	@Path("{id}")
	public String viewNote(@PathParam("id") String noteId) {
		
		Note note =  notesService.viewNote(noteId);
		
		if (note != null) {
			
            Map<String, Object> handlebarsValues = new HashMap<>();
            handlebarsValues.put("title", note.getTitle());
            handlebarsValues.put("text", note.getText());
            
            String tags = "";
            
            for (int i = 0; i < note.getTags().size(); i++) {
            	
            	tags += note.getTags().get(i);
            	
            	if (i < note.getTags().size() - 1) {
            		
            		tags += " ";
            	}
            }
            
            handlebarsValues.put("tags", tags);

            return HandlebarsUtil.processTemplate("note", handlebarsValues);

        } else {

            return "Note not found";
        }
	}
	
	@GET
	@Path("/search")
	public String search() {
		return "<h1>Enter a search term value</h1>";
	}
	
	@GET
	@Path("/search/{searchTerm}")
	public String filterBySearchTerm (@PathParam("searchTerm") String searchTerm) {
		
		Collection<Note> notes = notesService.filterBySearchTerm(searchTerm);
		
		Map<String, Object> handlebarsValues = new HashMap<>();
		handlebarsValues.put("pageTitle", "Notes found by selected search term");
	    handlebarsValues.put("notes", notes);

	    return HandlebarsUtil.processTemplate("filterednotes", handlebarsValues);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteNote (@PathParam("id") String id) {
		notesService.deleteNote(id);
	}
	
	@POST
	public void createNote (Note note) {
		
		notesService.createNote(note);
	}
	
	@PUT
	@Path("{id}")
	public void editNote (@PathParam("id") String id, Note note) {
		
		notesService.editnote(id, note);
	}
}

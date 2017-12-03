package com.zarebcn.notas.controllers;

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

@Path("/notes")
@Produces(MediaType.TEXT_HTML)
public class NotesControllerNew {
	
	NotesService notesService = new NotesService();
	
	public NotesControllerNew (NotesService notesService) {
		this.notesService = notesService;
	}
	
	@GET
	public String viewNotes() {
		return notesService.viewNotes();
	}
	
	@GET
	@Path("{id}")
	public String viewNote(@PathParam("id") int noteId) {
		return notesService.viewNote(noteId);
	}
	
	@GET
	@Path("/search")
	public String search() {
		return "<h1>Enter a search term value</h1>";
	}
	
	@GET
	@Path("/search/{searchTerm}")
	public String filterBySearchTerm (@PathParam("searchTerm") String searchTerm) {
		return notesService.filterBySearchTerm(searchTerm);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteNote (@PathParam("id") int id) {
		notesService.deleteNote(id);
	}
	
	@POST
	public void createNote (Note note) {
		
		notesService.createNote(note);
	}
	
	@PUT
	@Path("{id}")
	public void editNote (@PathParam("id") int id, Note note) {
		
		notesService.editnote(id, note);
	}
}

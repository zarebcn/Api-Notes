package com.zarebcn.notas.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zarebcn.notas.util.HandlebarsUtil;
import com.zarebcn.notas.model.Note;

@Path("/notes")
@Produces(MediaType.TEXT_HTML)
public class NotesController {
	
	private List<Note> notes;
	
	public NotesController() {
		
		notes = new ArrayList<>();
		
		notes.add(new Note(1, "Ir al médico", "Mañana tengo que ir al médico por la tarde", new ArrayList<>(Arrays.asList("#mañana", "#médico", "#tarde"))));
		notes.add(new Note(2, "Hacer la compra", "Tengo que ir al súper a hacer la compra por la tarde", new ArrayList<>(Arrays.asList("#compra", "#súper", "#tarde"))));
		notes.add(new Note(3, "ver el partido", "Mañana he quedado con unos amigos para ver el barça", new ArrayList<>(Arrays.asList("#mañana", "#amigos", "#barça"))));
	}
	
	@GET
	public String viewNotes() {
		
	        Map<String, Object> map = new HashMap<>();
	        
	        map.put("pageTitle", "Notes");
            map.put("notes", notes);

            return HandlebarsUtil.processTemplate("notes", map);

	}
	
	@GET
	@Path("{id}")
	public String viewNote(@PathParam("id") int noteId) {
		
		 if (noteId > 0 && noteId <= notes.size()) {

	            Note note = notes.get(noteId - 1);

	            Map<String, Object> map = new HashMap<>();
	            map.put("title", note.getTitle());
	            map.put("text", note.getText());
	            
	            String tags = "";
	            
	            for (int i = 0; i < note.getTags().size(); i++) {
	            	
	            	tags += note.getTags().get(i);
	            	
	            	if (i < note.getTags().size() - 1) {
	            		
	            		tags += ", ";
	            	}
	            }
	            
	            map.put("tags", tags);

	            return HandlebarsUtil.processTemplate("note", map);

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
		
		 Map<String, Object> map = new HashMap<>();
		 Map<Integer, Object> filteredNotes = new HashMap<>();
		
		for (int i = 0; i < notes.size(); i++) {
			
			if (notes.get(i).getTitle().toLowerCase().equals(searchTerm.toLowerCase()) || 
					notes.get(i).getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
				
				filteredNotes.put(notes.get(i).getId(), notes.get(i));
			}
			
			if (notes.get(i).getText().toLowerCase().equals(searchTerm.toLowerCase()) || 
					notes.get(i).getText().toLowerCase().contains(searchTerm.toLowerCase()) ) {
				
				filteredNotes.put(notes.get(i).getId(), notes.get(i));
			}
			
			for (int j = 0; j < notes.get(i).getTags().size(); j++) {
				
				if (notes.get(i).getTags().get(j).substring(1, notes.get(i).getTags().get(j).length())
						.toLowerCase().equals(searchTerm.toLowerCase())) {
					
					filteredNotes.put(notes.get(i).getId(), notes.get(i));
				}
			}
		}
		
		  map.put("pageTitle", "Notes found by selected search term");
	      map.put("notes", filteredNotes.values());
	      map.put("showHomeButton", true);

	      return HandlebarsUtil.processTemplate("notes", map);
	}

}

package com.zarebcn.notas.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import com.zarebcn.notas.model.Note;
import com.zarebcn.notas.util.HandlebarsUtil;

public class NotesService {
	
	private Map<Integer, Note> notes;
	private int nextId;
	
	public NotesService() {
		
		notes = new HashMap<>();
		
		notes.put(1, new Note(1, "Ir al médico", "Mañana tengo que ir al médico por la tarde", new ArrayList<>(Arrays.asList("#mañana", "#médico", "#tarde"))));
		notes.put(2, new Note(2, "Hacer la compra", "Tengo que ir al súper a hacer la compra por la tarde", new ArrayList<>(Arrays.asList("#compra", "#súper", "#tarde"))));
		notes.put(3, new Note(3, "ver el partido", "Mañana he quedado con unos amigos para ver el barça", new ArrayList<>(Arrays.asList("#mañana", "#amigos", "#barça"))));
		
		nextId = 4;
	}
	
	public String viewNotes() {
		
        Map<String, Object> map = new HashMap<>();
        
        map.put("pageTitle", "Notes");
        map.put("notes", notes.values());

        return HandlebarsUtil.processTemplate("notes", map);
	}
	
	public String viewNote(int noteId) {
		
		 if (noteId > 0 && noteId <= nextId - 1) {

	            Note note = notes.get(noteId);

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
	
	public String filterBySearchTerm (String searchTerm) {
		
		 Map<String, Object> map = new HashMap<>();
		 Map<Integer, Object> filteredNotes = new HashMap<>();
		
		for (int i = 1; i <= nextId - 1; ) {
			
			if (notes.get(i) != null) {
				
				if (notes.get(i).getTitle().toLowerCase().equals(searchTerm.toLowerCase()) || 
						notes.get(i).getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ) {
					
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
				
				i++;
				
			} else {
				i++;
			}
		}
		
		  map.put("pageTitle", "Notes found by selected search term");
	      map.put("notes", filteredNotes.values());

	      return HandlebarsUtil.processTemplate("filterednotes", map);
	}
	
	public void deleteNote (int id) {
		
		notes.remove(id);
	}
	
	public void createNote (Note note) {
		
		Note nota = new Note();
		
		nota.setId(nextId);
		nota.setTitle(note.getTitle());
		nota.setText(note.getText());
		nota.setTags(note.getTags());
		
		notes.put(nextId, nota);
		
		nextId++;
	}

}

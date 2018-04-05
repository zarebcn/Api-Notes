package com.zarebcn.notas.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.zarebcn.notas.model.Note;
import static com.zarebcn.notas.util.StringUtil.*;

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
	
	public Collection<Note> viewNotes() {
		
		return notes.values();
	}
	
	public Note viewNote(int noteId) {
		
		return notes.get(noteId);
	}
	
	public Collection<Note> filterBySearchTerm (String searchTerm) {
		
		List<Note> filteredNotes = new ArrayList<>();
		
		for (Integer id : notes.keySet()) {
			
			boolean foundTag = false;
			
			for (String tag : notes.get(id).getTags()) {
				
				if (containsIgnoreCase(tag, searchTerm)) {
					
					foundTag = true;
				}
			}
			
			if (foundTag || containsIgnoreCase(notes.get(id).getTitle(), searchTerm) ||
				 containsIgnoreCase(notes.get(id).getText(), searchTerm)) {
				
				filteredNotes.add(notes.get(id));	
			}
		}
		
		return filteredNotes;
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
	
	public void editnote (int id, Note note) {
		
		Note nota = new Note();
		nota.setId(id);
		nota.setTitle(note.getTitle());
		nota.setText(note.getText());
		nota.setTags(note.getTags());
		
		notes.put(id, nota);
	}

}

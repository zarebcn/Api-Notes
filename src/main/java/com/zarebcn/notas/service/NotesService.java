package com.zarebcn.notas.service;

import java.util.*;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zarebcn.notas.model.Note;
import static com.zarebcn.notas.util.ConverterToNote.*;


public class NotesService {
	
	MongoCollection<Document> notesCol;
	
	public NotesService() {
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase notesDb = mongoClient.getDatabase("notes");
		notesCol = notesDb.getCollection("notes");
	}
	
	public Collection<Note> viewNotes() {
		
		List<Note> notes = new ArrayList<>();
		
		notesCol.find().forEach((Document noteDoc) -> {
			
			notes.add(convertToNote(noteDoc));
		});
		
		return notes;
	}
	
	public Note viewNote(String noteId) {
		
		Document noteDoc = notesCol.find(new Document("_id", new ObjectId(noteId))).first();
		
		return convertToNote(noteDoc);
	}
	
	public Collection<Note> filterBySearchTerm (String searchTerm) {
		
		List<Note> filteredNotes = new ArrayList<>();
		
		Document clause1 = new Document("tags", Pattern.compile(searchTerm));  
		Document clause2 = new Document("title", Pattern.compile(searchTerm)); 
		Document clause3 = new Document("text", Pattern.compile(searchTerm)); 
		
		List<Document> or = new ArrayList<>();
		or.add(clause1);
		or.add(clause2);
		or.add(clause3);
		
		Document filterQuery = new Document("$or", or);
		
		notesCol.find(filterQuery).forEach((Document noteDoc) -> {
			
			filteredNotes.add(convertToNote(noteDoc));
		});
		
		return filteredNotes;
	}
	
	public void deleteNote (String id) {
		
		notesCol.deleteOne(new Document("_id", new ObjectId(id)));
	}
	
	public void createNote (Note note) {
		
		Document newDoc = new Document("title", note.getTitle()).append("text", note.getText()).append("tags", note.getTags());
		
		notesCol.insertOne(newDoc);
	}
	
	public void editnote (String id, Note note) {
		
		Document docId = new Document("_id", new ObjectId(id));
		Document editedDoc = new Document("title", note.getTitle()).append("text", note.getText()).append("tags", note.getTags());
		Document updateQuery = new Document("$set", editedDoc);
		
		notesCol.updateOne(docId, updateQuery);
	}

}

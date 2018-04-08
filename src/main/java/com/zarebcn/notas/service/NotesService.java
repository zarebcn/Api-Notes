package com.zarebcn.notas.service;

import java.util.*;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
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
			
			notes.add(converterToNote(noteDoc));
		});
		
		return notes;
	}
	
	public Note viewNote(String noteId) {
		
		Document noteDoc = notesCol.find(new Document("_id", new ObjectId(noteId))).first();
		
		return converterToNote(noteDoc);
	}
	
	public Collection<Note> filterBySearchTerm (String searchTerm) {
		
		List<Note> filteredNotes = new ArrayList<>();
		
		DBObject clause1 = new BasicDBObject("tags", searchTerm);  
		DBObject clause2 = new BasicDBObject("title", Pattern.compile(searchTerm)); 
		DBObject clause3 = new BasicDBObject("text", Pattern.compile(searchTerm)); 
		BasicDBList or = new BasicDBList();
		or.add(clause1);
		or.add(clause2);
		or.add(clause3);
		DBObject query = new BasicDBObject("$or", or);
		
		FindIterable<Document> cur = notesCol.find((Bson) query);
		
		cur.forEach((Document noteDoc) -> {
			
			filteredNotes.add(converterToNote(noteDoc));
		});
		
		return filteredNotes;
	}
	
	public void deleteNote (String id) {
		
		notesCol.deleteOne(new Document("_id", new ObjectId(id)));
	}
	
	public void createNote (Note note) {
		
		BasicDBObject newDoc = new BasicDBObject("title", note.getTitle()).append("text", note.getText()).append("tags", note.getTags());
		
		notesCol.insertOne(new Document(newDoc));
	}
	
	public void editnote (String id, Note note) {
		
		BasicDBObject docId = new BasicDBObject("_id", new ObjectId(id));
		BasicDBObject editedDoc = new BasicDBObject("title", note.getTitle()).append("text", note.getText()).append("tags", note.getTags());
		BasicDBObject updateQuery = new BasicDBObject("$set", editedDoc);
		
		notesCol.updateOne(docId, updateQuery);
	}

}

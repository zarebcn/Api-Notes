package com.zarebcn.notas.util;

import java.util.List;

import org.bson.Document;
import com.zarebcn.notas.model.Note;

public class ConverterToNote {
	
	public static Note converterToNote (Document noteDoc) {
		
		Note note = new Note();
		note.setId( (String) noteDoc.getObjectId("_id").toString() );
		note.setTitle( (String) noteDoc.get("title") );
		note.setText( (String) noteDoc.get("text") );
		note.setTags( (List<String>) noteDoc.get("tags") );
		
		return note;
	}
}

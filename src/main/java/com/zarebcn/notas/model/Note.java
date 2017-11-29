package com.zarebcn.notas.model;

import java.util.ArrayList;
import java.util.List;

public class Note {
	
	private int id;
	private String title;
	private String text;
	private List<String> tags = new ArrayList<>();
	
	public Note (int id, String title, String text, List<String> tags) {
		
		this.id = id;
		this.title = title;
		this.text = text;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}

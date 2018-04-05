package com.zarebcn.notas.util;

public class StringUtil {
	
	public static Boolean containsIgnoreCase (String text, String searchTerm) {
		
		return text.toLowerCase().contains(searchTerm.toLowerCase());
	}
}

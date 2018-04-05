package com.zarebcn.notas.util;

public class StringUtil {
	
	public static Boolean containsIgnoreCase (String text, String subText) {
		
		return text.toLowerCase().contains(subText.toLowerCase());
	}
}

package com.zarebcn.notas.util;

public class StringUtil {
	
	public static boolean containsIgnoreCase (String text, String subText) {
		
		return text.toLowerCase().contains(subText.toLowerCase());
	}
}

package com.zarebcn.notas.util;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandlebarsUtil {
	
	 private static final Handlebars handlebars = buildHandlebars();

	    /**
	     * Takes template from filename and substitutes the placeholders using the given value.
	     * The value can be a collection, a map or another object (getters will be used).
	     */
	    public static String processTemplate(String filename, Object value) {

	        try {
	            final Template template = handlebars.compile(filename);
	            return template.apply(value);
	        } catch (IOException e) {
	            throw new RuntimeException("Error while writing template result", e);
	        }
	    }

	    private static Handlebars buildHandlebars() {

	        final TemplateLoader loader = new ClassPathTemplateLoader("/templates/handlebars/", ".hbs");
	        return new Handlebars(loader);
	    }
}

package com.zarebcn.notas;



import com.zarebcn.notas.controllers.NotesController;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NotesApplication extends Application<NotesConfiguration> {
	
	 public static void main(String[] args) throws Exception {
	        new NotesApplication().run(args);
	    }

	    @Override
	    public String getName() {
	        return "Notes";
	    }

	    @Override
	    public void initialize(Bootstrap<NotesConfiguration> bootstrap) {

	        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
	    }

	    @Override
	    public void run(NotesConfiguration configuration, Environment environment) {

	        NotesController notesController = new NotesController();

	        //tell dropwizard to setup my resource
	        environment.jersey().register(notesController);
	    }
}

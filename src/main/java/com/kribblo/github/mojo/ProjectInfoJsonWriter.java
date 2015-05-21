package com.kribblo.github.mojo;

import com.google.gson.*;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.*;

public class ProjectInfoJsonWriter {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private final File outputFile;

	public ProjectInfoJsonWriter(File outputFile) {
		this.outputFile = outputFile;
	}

	public void writeJsonToFile(ProjectInfo projectInfo) throws MojoExecutionException {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(outputFile);
			GSON.toJson(projectInfo, fileWriter);
			fileWriter.close();
		} catch (IOException | JsonIOException e) {
			throw new MojoExecutionException("Could not write to file: " + outputFile, e);
		}
	}
}

package com.kribblo.github.mojo;

import org.apache.maven.plugin.*;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.io.File;

@Mojo(name = "project-info", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class ProjectInfoJsonMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject mavenProject;

	@Parameter(defaultValue = "${project.build.directory/project-info.json}")
	private File outputFile;

	@Parameter(required = false)
	private String groupIdFilter;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		ProjectInfoFactory factory = new ProjectInfoFactory(mavenProject);
		ProjectInfo projectInfo = factory.getProjectInfo(groupIdFilter);

		ProjectInfoJsonWriter jsonWriter = new ProjectInfoJsonWriter(outputFile);
		jsonWriter.writeJsonToFile(projectInfo);

		Log log = getLog();
		log.info("Wrote JSON info for " + projectInfo + " to " + outputFile);
	}
}

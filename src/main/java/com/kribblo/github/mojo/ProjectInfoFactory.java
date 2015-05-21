package com.kribblo.github.mojo;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;

import java.util.*;

public class ProjectInfoFactory {
	private static final String COMPILE_SCOPE = "compile";

	private final MavenProject mavenProject;

	public ProjectInfoFactory(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public ProjectInfo getProjectInfo(String groupIdFilter) {
		ProjectInfo projectInfo = new ProjectInfo();

		projectInfo.artifactId = mavenProject.getArtifactId();
		projectInfo.groupId = mavenProject.getGroupId();
		projectInfo.name = mavenProject.getName();
		projectInfo.version = mavenProject.getVersion();
		projectInfo.description = mavenProject.getDescription();

		projectInfo.dependencies = new HashSet<>();

		if (groupIdFilter != null) {
			projectInfo.groupIdFilter = groupIdFilter;
			projectInfo.filteredDependencies = new HashSet<>();
		}

		Set<Artifact> dependencyArtifacts = mavenProject.getDependencyArtifacts();

		for (Artifact dependencyArtifact : dependencyArtifacts) {
			String scope = dependencyArtifact.getScope();

			if (COMPILE_SCOPE.equals(scope)) {
				String dependency = makeDependencyString(dependencyArtifact);
				projectInfo.dependencies.add(dependency);
				if (groupIdFilter != null && dependencyArtifact.getGroupId().startsWith(groupIdFilter)) {
					projectInfo.filteredDependencies.add(dependency);
				}
			}
		}

		return projectInfo;
	}

	private String makeDependencyString(Artifact dependencyArtifact) {
		String groupId = dependencyArtifact.getGroupId();
		String artifactId = dependencyArtifact.getArtifactId();
		String version = dependencyArtifact.getVersion();

		return groupId + ":" + artifactId + ":" + version;
	}
}

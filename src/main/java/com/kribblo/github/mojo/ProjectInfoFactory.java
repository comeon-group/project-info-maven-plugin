package com.kribblo.github.mojo;

import com.google.common.base.*;
import com.google.common.collect.*;
import org.apache.maven.artifact.*;
import org.apache.maven.project.*;

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

		Set<Artifact> dependencyArtifacts = getDependencyArtifacts(groupIdFilter);

		for (Artifact dependencyArtifact : dependencyArtifacts) {
			String scope = dependencyArtifact.getScope();

			if (COMPILE_SCOPE.equals(scope)) {
				String dependency = makeDependencyString(dependencyArtifact);
				projectInfo.dependencies.add(dependency);
			}
		}

		return projectInfo;
	}

	private Set<Artifact> getDependencyArtifacts(final String groupIdFilter) {
		Set<Artifact> dependencyArtifacts = mavenProject.getDependencyArtifacts();

		if(groupIdFilter != null) {
			return Sets.filter(dependencyArtifacts, new Predicate<Artifact>() {
				@Override
				public boolean apply(Artifact artifact) {
					return artifact.getGroupId().startsWith(groupIdFilter);
				}
			});
		}

		return dependencyArtifacts;
	}

	private String makeDependencyString(Artifact dependencyArtifact) {
		String groupId = dependencyArtifact.getGroupId();
		String artifactId = dependencyArtifact.getArtifactId();
		String version = dependencyArtifact.getVersion();

		return groupId + ":" + artifactId + ":" + version;
	}
}

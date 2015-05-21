package com.kribblo.github.mojo;

import java.util.Set;

public class ProjectInfo {
	public String artifactId;
	public String groupId;
	public String version;
	public String name;
	public String description;
	public Set<String> dependencies;

	public String groupIdFilter;
	public Set<String> filteredDependencies;

	@Override
	public String toString() {
		return groupId + ":" + artifactId + ":" + version;
	}
}

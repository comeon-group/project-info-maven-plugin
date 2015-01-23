package com.kribblo.github.mojo;

import java.util.*;

public class ProjectInfo {
	public String artifactId;
	public String groupId;
	public String version;
	public String name;
	public String description;
	public Set<String> dependencies = new HashSet<>();

	@Override
	public String toString() {
		return groupId + ":" + artifactId + ":" + version;
	}
}

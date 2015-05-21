# project-info-maven-plugin

Light-weight project information extractor, for reporting optionally filtered dependencies and some other things on a project. Useful for collecting version information and who depends on who in an environment with multiple servers, perhaps many many small services.

## Minimal Example

Add to pom.xml:

```xml
<plugin>
    <groupId>com.comeon.mojo</groupId>
    <artifactId>project-info-maven-plugin</artifactId>
    <version>1.0.0</version>
</plugin>
```

Then run:

    mvn project-info:project-info

The file `target/project-info.json` is created. See below for configuration.

# Add to build

Default phase is `generate-resources`.

```xml
<plugin>
	<groupId>com.comeon.mojo</groupId>
	<artifactId>project-info-maven-plugin</artifactId>
	<version>1.0.0</version>
    <executions>
        <execution>
            <id>project-info</id>
            <goals>
                <goal>project-info</goal>
            </goals>
            <configuration>
                <outputFile>${project.basedir}/project-info.json</outputFile>
                <groupIdFilter>com.comeon</groupIdFilter>
            </configuration>
        </execution>
    </executions>
</plugin>
```

# Configuration

All configuration is optional.

* `outputFile` sets the output JSON file. Defaults to `${project.build.directory/project-info.json}`
* `groupIdFilter` limits dependencies to groupIds with this prefix, perhaps used to only list internal dependencies, such as `com.comeon`.

# JSON Output

For this project:

```json
{
  "artifactId": "project-info-maven-plugin",
  "groupId": "com.comeon.mojo",
  "version": "1.0.0",
  "name": "project-info-maven-plugin",
  "description": "Light-weight project information extractor, for reporting optionally filtered dependencies and some other things on a project. Useful for collecting version information and who depends on who in an environment with multiple servers, perhaps many many small services.",
  "dependencies": [
    "org.apache.maven:maven-plugin-api:3.2.5",
    "org.apache.maven:maven-project:2.2.1",
    "com.google.code.gson:gson:2.3.1",
    "org.apache.maven.plugin-tools:maven-plugin-annotations:3.4"
  ]
}
```

With groupIdFilter set to `"com.google"`:

```json
{
  "artifactId": "project-info-maven-plugin",
  "groupId": "com.comeon.mojo",
  "version": "1.0.0",
  "name": "project-info-maven-plugin",
  "description": "Light-weight project information extractor, for reporting optionally filtered dependencies and some other things on a project. Useful for collecting version information and who depends on who in an environment with multiple servers, perhaps many many small services.",
  "dependencies": [
    "org.apache.maven:maven-plugin-api:3.2.5",
    "org.apache.maven:maven-project:2.2.1",
    "com.google.code.gson:gson:2.3.1",
    "org.apache.maven.plugin-tools:maven-plugin-annotations:3.4"
  ],
  "groupIdFilter": "com.google",
  "filteredDependencies": [
    "com.google.code.gson:gson:2.3.1"
  ]
}
```

## project-info-json-mojo - Maven Mojo for generating dependency info into a JSON file

Generates some dependency information about a build that can be queried later. Useful for collecting version information
and who depends on who in an environment with multiple servers, perhaps many many small services.

### Build & Install

    mvn clean install

### Run from command line

    mvn com.github.kribblo.mojo:project-info-json-mojo:1.0:project-info

### Use as plugin

#### Example

```xml
<plugin>
    <groupId>com.github.kribblo.mojo</groupId>
    <artifactId>project-info-json-mojo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
        <execution>
            <id>project-info</id>
            <goals>
                <goal>project-info</goal>
            </goals>
            <configuration>
                <outputFile>${basedir}/info.json</outputFile>
                <groupIdFilter>com.google</groupIdFilter>
            </configuration>
        </execution>
    </executions>
</plugin>
```

#### Run with:

    mvn generate-resources

Or as part of normal package/verify/install.

#### Configuration

* `outputFile` sets the output JSON file.
* `groupIdFilter` limits dependencies to groupIds starting with this value, usually used to only list internal dependencies.


### Output

Writes to `target/project-info.json` (`${project.build.directory/project-info.json}`) by default.

The output for this project:

```json
{
    "artifactId": "project-info-json-mojo",
    "groupId": "com.github.kribblo.mojo",
    "version": "1.0",
    "name": "project-info-json-mojo",
    "dependencies": [
        "org.apache.maven:maven-project:2.2.1",
        "com.google.guava:guava:18.0",
        "com.google.code.gson:gson:2.3.1",
        "org.apache.maven.plugin-tools:maven-plugin-annotations:3.4",
        "org.apache.maven:maven-plugin-api:3.2.5"
    ]
}
```

With groupIdFilter set to `"com.google"`:

```json
{
    "artifactId": "project-info-json-mojo",
    "groupId": "com.github.kribblo.mojo",
    "version": "1.0",
    "name": "project-info-json-mojo",
    "dependencies": [
        "com.google.guava:guava:18.0",
        "com.google.code.gson:gson:2.3.1"
    ]
}
```

### Testing

Should have tests, however maven plugin test methods seems to be broken with no solutions in sight, looking at Stackoverflow etc.
Making do with manual testing for now.

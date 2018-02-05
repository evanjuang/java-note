maven - useful plugins
===

## 1. properties-maven-plugin 
* Read properties from file
```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>properties-maven-plugin</artifactId>
  <version>1.0.0</version>
  <executions>
    <execution>
      <phase>initialize</phase>
      <goals>
        <goal>read-project-properties</goal>
      </goals>
      <configuration>
        <files>
          <file>src/main/resources/config.properties</file>
        </files>
      </configuration>
    </execution>
  </executions>
</plugin>
```
## 2. maven-surefire-plugin
* Skip junit test
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>2.19.1</version>
  <configuration>
    <skipTests>True</skipTests>
  </configuration>
</plugin>
```

### Install
1. Download Nexus OSS <br>
    `http://www.sonatype.org/nexus/downloads/` <br>
    current version: `nexus-3.2.0-01-unix.tar.gz`
2. Extract tar.gz file

### Run
1. cd `nexus-3.2.0-01/bin`
2. `./nexus start`
3. Open browser and link to `http://localhost:8081`
4. Sign in: `admin/admin123`

### Create new repository
1. `Server administration and configuration` > `Repositories` > `Create repository`
2. Choose `maven2 (hosted)`
3. Set `Name` and `Version policy`

### Deplopy to Nexus
1. Add Nexus login information in maven setting file (`{user dir}/.m2/settings.xml`)
```xml
  <settings>
    <servers>
      <server>
        <id>snapshots</id>
        <username>admin</username>
        <password>admin123</password>
      </server>
    </servers>
  </settings>
```


2. In project POM file, add deploy setting<br>
  The `<id>` is reference to `settings.xml`
```xml
  <project>
    <distributionManagement>
      <repository>
        <id>snapshots</id>
        <name>Internal Snapshots</name>
        <url>{user nexus host ip}</url>
      </repository>
    </distributionManagement>
  </project>
```


3. deploy
`mvn deploy`

### Download from Nexus
1. In project POM file, add repository setting
```xml
<project>
 <repositories>
    <repository>
      <id>nexus</id>
        <name>nexus</name>
        <url>{user nexus repository ip}</url>
        <releases><enabled>true</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>com.test</groupId>
      <artifactId>nexus-test-util</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
  </dependencies>
  
</project>
```

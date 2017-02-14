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

2. In project POM file, add deploy setting
```xml
<project>
  <distributionManagement>
    <repository>
      <id>snapshots</id>
      <name>Internal Snapshots</name>
      <url>http://10.6.224.195:8081/repository/sw3-cmf-snapshot/</url>
    </repository>
  </distributionManagement>
</project>
```

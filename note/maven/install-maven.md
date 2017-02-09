###Install
1. download link<br>
    `http://maven.apache.org/download.cgi`
2. unzip to C:\Program Files\apache-maven-3.3.3
3. add "C:\Program Files\apache-maven-3.3.3\bin" to PATH
4. execute `mvn -ver` to check

###Setting
* global config file: `%M2_HOME%/conf/settings.xml`
* user config file: `${user.home}/.m2/settings.xml`
* local repository: `${user.home}/.m2/repository`
* set proxy:
    1. create settings.xml in ${user.home}/.m2/
    2. add proxy config
    ```xml
    <settings>
     <proxies>
        <proxy>
          <id>{id}</id>
          <active>true</active>
          <protocol>http</protocol>
          <host>{proxy-host}</host>
          <port>{proxy-port}</port>
          <username>{user}</username>
          <password>{pwd}</password>
        </proxy>
      </proxies>
    </settings>
    ```
    
### Eclipse plugin: M2E
1. `http://download.eclipse.org/technology/m2e/releases/`
2. Maven > Installations <br>
    Add installed maven
3. Maven > User Settings <br>
    Check Local Repository and User Settings

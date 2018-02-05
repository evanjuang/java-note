Log4j2 HowTo
=====

## 1. Add Maven Dependency
```xml
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.3</version>
</dependency>
```
## 2. Configure Log4j2.xml
Put the Log4j2.xml to  `src/main/resources`

### **Log4j2.xml:**
```xml
<Configuration>
    <!--variable-->
    <Properties>
        <Property name="LOG_HOME">./logs</Property>>
    </Properties>
    <!--output setting-->
    <Appenders>
        <RollingFile name="file" fileName="${LOG_HOME}/test.log" filePattern="${LOG_HOME}/test-%d{yyyy-MM-dd}.%i.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss}]  %-5p [%c{1}] %m%n"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="100 KB"/>
            </Policies>
        </RollingFile>
        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout pattern="%highlight{[%d{yyyy-MM-dd HH:mm:ss}]  %-5p [%c{1}] %m%n}"/>
        </Console>
    </Appenders>
    <!--logger setting-->
    <Loggers>
        <Root level="DEBUG">
            <AppenderRef ref="file"/>
            <AppenderRef ref="console"/>
        </Root>
    </Loggers>
</Configuration>
```

## 3. Using Log4j2
```java
public class FooClass {
    static final Logger log = LogManager.getLogger(FooClass.class.getName());
    public static void bar() {
        log.debug("debug message");
        log.info("information message ");
        log.printf(Level.DEBUG, "Format String: %s\n", strVariable);
    }
}
```
Log exception dump
```java
try {
    /*code...*/
} catch (IOException e) {
    log.error("I/O:" + e.getMessage());
    log.debug(e.getMessage(), e);
}
```

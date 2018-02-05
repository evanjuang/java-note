```java
ClientConfig config = new ClientConfig();
config.register(getJsonProvider());
```
```java
private JacksonJsonProvider getJsonProvider() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return new JacksonJsonProvider(mapper);
}
```

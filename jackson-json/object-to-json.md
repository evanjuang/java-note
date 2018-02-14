* to JSON string
  ```java
    public static <T> String toJson(T object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonParserException("object to json - " + e.getMessage(), e);
        }
    }
  ```
* to pretty JSON string
  ```java
      public static <T> String toPrettyJson(T object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParserException("object to pretty json - " + e.getMessage(), e);
        }
    }
  ```


```java
public class InvalidValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String value;

    public InvalidValueException(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public InvalidValueException(String name, String value, Throwable e) {
        super(e);
        this.name = name;
        this.value = value;
    }

    public InvalidValueException(String msg, String name, String value) {
        super(msg);
        this.name = name;
        this.value = value;
    }

    public InvalidValueException(String msg, String name, String value, Throwable e) {
        super(msg);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
```

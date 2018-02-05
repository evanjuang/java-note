* String.format() takes an array as a single argument
```java
String f = "Mi name is %s %s."; 
System.out.println(String.format(f, (Object[])new String[{"John","Connor"}));
```

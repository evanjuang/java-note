```java
Map<String, Object> map = ...; 

// need key 
for (String key : map.keySet()) {} 

// need value 
for (Object value : map.values()) {} 

// need key & value 
for (Map.Entry<String, Object> entry : map.entrySet()) { 
    String key = entry.getKey(); 
    Object value = entry.getValue(); 
} 

// need remove element 
public static void printMap(Map mp) { 
    Iterator it = mp.entrySet().iterator(); 
    while (it.hasNext()) { 
        Map.Entry pair = (Map.Entry)it.next();
		System.out.println(pair.getKey() + " = " + pair.getValue());
		it.remove(); // avoids a ConcurrentModificationException
    }
}
```

Reference
https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map

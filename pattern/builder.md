
### Builder pattern
> https://jlordiales.me/2012/12/13/the-builder-pattern-in-practice/
```java
public class User {
  private final String firstName; // required
  private final String lastName; // required
  private final int age; // optional
  private final String phone; // optional
  private final String address; // optional

  private User(UserBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.age = builder.age;
    this.phone = builder.phone;
    this.address = builder.address;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public static class UserBuilder {
    private final String firstName;
    private final String lastName;
    private int age;
    private String phone;
    private String address;

    public UserBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public UserBuilder age(int age) {
      this.age = age;
      return this;
    }

    public UserBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public UserBuilder address(String address) {
      this.address = address;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
```
```java
public User getUser() {
  return new
    User.UserBuilder("Jhon", "Doe")
    .age(30)
    .phone("1234567")
    .address("Fake address 1234")
    .build();
}
```
### 簡化的Builer pattern
> http://www.wisedream.net/2016/01/20/programming/simplify-your-java-code/
```java
public class Person {
    @Getter
    private String name;
    @Getter
    private String id;
    @Getter
    private int age;
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public static Person of(String name, String id){
        return new Person(name, id);
    }
    public Person withAge(int age) {
        if (age > 120) {
            throw new IllegalStateException("Age out of range"); // thread-safe
        }
        this.age = age;
        return this;
    }
}
```

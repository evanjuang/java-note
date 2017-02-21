import com.test.builder.lb.Address;
import com.test.builder.lb.Person;
//import com.test.builder.basic.Address;
//import com.test.builder.basic.Person;

public class Main {

    public static void main(String[] args) {

        Person person = Person.builder()
                .name("myname")
                .address(Address.builder()
                        .streetAddress("my street")
                        .city("my city")
                        .state("my stat")
                        .build()
                 )
                .build();

        System.out.println(person.toString());
    }
}

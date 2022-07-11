import lombok.Data;

import java.util.Date;
@Data
public class Person {
    private String name;
    private String surname;
    private String nationality;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String fiscalCode;
    private String documentNumber;
    private String address;
    private String city;
    private String phoneNumber;
    private String emailAddress;
    private Gender gender;
}

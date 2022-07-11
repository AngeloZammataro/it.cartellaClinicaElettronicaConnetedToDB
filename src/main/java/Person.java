import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
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

    public Person(String name, String surname, String nationality, String placeOfBirth, Date dateOfBirth, String fiscalCode, String documentNumber, String address, String city, String phoneNumber, String emailAddress, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.fiscalCode = fiscalCode;
        this.documentNumber = documentNumber;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = gender;
    }
}

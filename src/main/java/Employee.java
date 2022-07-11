import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Employee extends Person{
    private String login;
    private String password;
    private RoleInClinic roleInClinic;
    private String badgeNumber;
    private PlaceOfWork placeOfWork;

    public Employee(String name, String surname, String nationality, String placeOfBirth, Date dateOfBirth, String fiscalCode, String documentNumber, String address, String city, String phoneNumber, String emailAddress, Gender gender, String login, String password, RoleInClinic roleInClinic, String badgeNumber, PlaceOfWork placeOfWork) {
        super(name, surname, nationality, placeOfBirth, dateOfBirth, fiscalCode, documentNumber, address, city, phoneNumber, emailAddress, gender);
        this.login = login;
        this.password = password;
        this.roleInClinic = roleInClinic;
        this.badgeNumber = badgeNumber;
        this.placeOfWork = placeOfWork;
    }
}

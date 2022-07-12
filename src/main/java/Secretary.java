import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Secretary extends Employee{
    private SecretaryRole secretaryRole;

    public Secretary(String name, String surname, String nationality, String placeOfBirth, LocalDate dateOfBirth, String fiscalCode, String documentNumber, String address, String city, String phoneNumber, String emailAddress, Gender gender, String login, String password, RoleInClinic roleInClinic, String badgeNumber, PlaceOfWork placeOfWork, SecretaryRole secretaryRole) {
        super(name, surname, nationality, placeOfBirth, dateOfBirth, fiscalCode, documentNumber, address, city, phoneNumber, emailAddress, gender, login, password, roleInClinic, badgeNumber, placeOfWork);
        this.secretaryRole = secretaryRole;
    }
}

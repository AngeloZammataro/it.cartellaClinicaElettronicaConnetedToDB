import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Employee{
    private MedicalSpecializzation medicalSpecializzation;

    public Doctor(String name, String surname, String nationality, String placeOfBirth, LocalDate dateOfBirth, String fiscalCode, String documentNumber, String address, String city, String phoneNumber, String emailAddress, Gender gender, String login, String password, RoleInClinic roleInClinic, String badgeNumber, PlaceOfWork placeOfWork, MedicalSpecializzation medicalSpecializzation) {
        super(name, surname, nationality, placeOfBirth, dateOfBirth, fiscalCode, documentNumber, address, city, phoneNumber, emailAddress, gender, login, password, roleInClinic, badgeNumber, placeOfWork);
        this.medicalSpecializzation = medicalSpecializzation;
    }
}

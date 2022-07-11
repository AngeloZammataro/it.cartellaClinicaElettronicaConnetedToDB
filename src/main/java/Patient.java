import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Patient extends Person{
    private String medicalPathology;

    @Builder
    public Patient(String name, String surname, String nationality, String placeOfBirth, Date dateOfBirth, String fiscalCode, String documentNumber, String address, String city, String phoneNumber, String emailAddress, Gender gender,String medicalPathology) {
        super(name,surname,nationality,placeOfBirth,dateOfBirth,fiscalCode,documentNumber,address,city,phoneNumber,emailAddress,gender);
        this.medicalPathology = medicalPathology;
    }
}

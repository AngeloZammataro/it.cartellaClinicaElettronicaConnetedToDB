import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    //private LocalDateTime dateTimeOfAppointment;
    private LocalDate dateOfAppointment;
    private Doctor doctor;
    private Patient patient;
    private String medicalExaminationReason;
}

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private LocalDate dateOfAppointment;
    private LocalTime dateTimeOfAppointment;
    private Doctor doctor;
    private Patient patient;
    private String medicalExaminationReason;
}

import lombok.Data;

import java.util.Date;
@Data
public class Appointment {
    private Date dateOfAppointment;
    private Doctor doctor;
    private Patient patient;
    private String medicalExaminationReason;
}

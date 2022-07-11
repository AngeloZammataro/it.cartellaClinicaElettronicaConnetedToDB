import lombok.Data;

import java.util.Date;
@Data
public class MedicalReport {
    private Doctor doctor;
    private Patient patient;
    private Date dateOfReport;
    private String medicalExams;
    private String anamnesis;
    private String prognosis;
    private String therapy;
}

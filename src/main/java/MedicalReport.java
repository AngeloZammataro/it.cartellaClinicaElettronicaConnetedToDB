import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalReport {
    private Doctor doctor;
    private Date dateOfReport;
    private String medicalExams;
    private String anamnesis;
    private int prognosis;
    private String therapy;
}

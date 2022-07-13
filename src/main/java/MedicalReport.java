import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalReport {
    private Doctor doctor;
    private LocalDate dateOfmedicalReport;
    private String medicalExams;
    private String anamnesis;
    private String diagnosis;
    private String therapy;
    private int prognosis;

}

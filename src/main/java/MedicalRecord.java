import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    private LocalDate date;
    private Patient patient;
    private List<MedicalReport> ListOfMedicalReport = new ArrayList<>();
}

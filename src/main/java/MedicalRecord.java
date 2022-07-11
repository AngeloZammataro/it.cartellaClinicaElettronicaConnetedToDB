import com.mysql.cj.LicenseConfiguration;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MedicalRecord {
    private Patient patient;
    private List<MedicalReport> ListOfMedicalReport = new ArrayList<>();
}

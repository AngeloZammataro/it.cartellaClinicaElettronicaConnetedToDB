import lombok.Data;

@Data
public class Employee extends Person{
    private String login;
    private String password;
    private RoleInClinic roleInClinic;
    private String badgeNumber;
    private PlaceOfWork placeOfWork;
}

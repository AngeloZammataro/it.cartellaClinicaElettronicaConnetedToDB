import lombok.*;

import java.sql.*;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
public class MainTest {
    public static void main(String[] args) {


        Date date = new Date(1976-12-10);
        Date date2 = new Date(1956-01-05);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        String formattedDatePatient = simpleDateFormat.format(date2);
        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        java.sql.Date datePatient1 = java.sql.Date.valueOf(formattedDatePatient);

        Doctor doctor = new Doctor("Bruce","Banner","United States","New York",date1,
                "SDFGHJK615166L","KA452262", "Via Verdi, 14","New York",
                "3336598552","3336965698",Gender.MALE,"greenForever",
                "5pacc4",RoleInClinic.DOCTOR,"BRU456BAN",PlaceOfWork.NAPOLI,
                MedicalSpecializzation.NEUROLOGY);
/*
        doctor.setName("Bruce");
        doctor.setSurname("Banner");
        doctor.setNationality("United States");
        doctor.setPlaceOfBirth("New York");
        doctor.setDateOfBirth(date1);
        doctor.setFiscalCode("SDFGHJK615166L");
        doctor.setDocumentNumber("KA452262");
        doctor.setAddress("Via Verdi 14");
        doctor.setGender(Gender.MALE);
        doctor.setCity("New York");
        doctor.setPhoneNumber("3336965698");
        doctor.setEmailAddress("gammaray@yahoo.com");
        doctor.setRoleInClinic(RoleInClinic.DOCTOR);
        doctor.setLogin("greenForever");
        doctor.setPassword("5pacc4");
        doctor.setBadgeNumber("BRU456BAN");
        doctor.setPlaceOfWork(PlaceOfWork.NAPOLI);
        doctor.setMedicalSpecializzation(MedicalSpecializzation.NEUROLOGY);
*/
        Patient patient = new Patient("Sara","Connor","United Kindom","London",
                datePatient1,"sracnr54de85fa","na456971","Via delle Alpi, 1",
                "Palermo","3256988745","gammaray@yahoo.com",Gender.FEMALE,
                "headache");

/*
        patient.setName("Sara");
        patient.setSurname("Connor");
        patient.setNationality("United Kindom");
        patient.setPlaceOfBirth("London");
        patient.setDateOfBirth(datePatient1);
        patient.setFiscalCode("sracnr54de85fa");
        patient.setDocumentNumber("na456971");
        patient.setAddress("Via delle Alpi, 1");
        patient.setGender(Gender.FEMALE);
        patient.setCity("London");
        patient.setPhoneNumber("3256988745");
        patient.setEmailAddress("gammaray@yahoo.com");
        patient.setMedicalPathology("headache");
*/
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it.cartellaClinicaElettronicaConnetedToDB", "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Drop table and view for test
            String query0 = "DROP TABLE IF EXISTS `doctor`;";
            statement.execute(query0);
            String query01 = "DROP TABLE IF EXISTS `patient`;";
            statement.execute(query01);

            //Create a table doctor
            String createTableDoctorQuery = "CREATE TABLE doctor("
                    + "doctorId INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR (20) NOT NULL, "
                    + "surname VARCHAR (20) NOT NULL, "
                    + "nationality VARCHAR (20) NOT NULL, "
                    + "placeOfBirth VARCHAR (20) NOT NULL, "
                    + "dateOfBirth DATETIME NOT NULL, "
                    + "fiscalCode VARCHAR (16) NOT NULL, "
                    + "documentNumber VARCHAR (10) NOT NULL, "
                    + "address VARCHAR (30) NOT NULL, "
                    + "city VARCHAR (20) NOT NULL, "
                    + "phoneNumber VARCHAR (20) NOT NULL, "
                    + "emailAddress VARCHAR (20) NOT NULL, "
                    + "gender ENUM('MALE','FEMALE'), "
                    + "roleInClinic ENUM('DOCTOR','SECRETARY','PATIENT','GUEST'), "
                    + "login VARCHAR (20) NOT NULL, "
                    + "password VARCHAR (20) NOT NULL, "
                    + "badgeNumber VARCHAR (10) NOT NULL, "
                    + "placeOfWork ENUM('MILANO','ROMA','PALERMO','NAPOLI'), "
                    + "medicalSpecializzation ENUM('CARDIOLOGY','DERMATOLOGY','ENDOCRINOLOGY','GASTROENTOROLOGY'," +
                    "'NEUROLOGY','PULMUNOLOGY','UROLOGY','RHEUMATOLOGY','ORTHOPAEDICS','RADIOLOGY'), "
                    + "PRIMARY KEY (doctorId))";
            //Execute query
            statement.execute(createTableDoctorQuery);
            System.out.println("The table 'doctor' was created!");

            //Create a table patient
            String createTablePatientQuery = "CREATE TABLE patient("
                    + "patientId INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR (20) NOT NULL, "
                    + "surname VARCHAR (20) NOT NULL, "
                    + "nationality VARCHAR (20) NOT NULL, "
                    + "placeOfBirth VARCHAR (20) NOT NULL, "
                    + "dateOfBirth DATETIME NOT NULL, "
                    + "fiscalCode VARCHAR (16) NOT NULL, "
                    + "documentNumber VARCHAR (10) NOT NULL, "
                    + "address VARCHAR (30) NOT NULL, "
                    + "city VARCHAR (20) NOT NULL, "
                    + "phoneNumber VARCHAR (20) NOT NULL, "
                    + "emailAddress VARCHAR (20) NOT NULL, "
                    + "gender ENUM('MALE','FEMALE'), "
                    + "medicalPathology VARCHAR (50) NOT NULL, "
                    + "PRIMARY KEY (patientId))";
            //Execute query
            statement.execute(createTablePatientQuery);

            System.out.println("The table 'patient' was created!");

            System.out.println("Inserting records into the table 'doctor'");

            //prepare String with placeholder
            String sqlX = "INSERT INTO doctor(name,surname,nationality,placeOfBirth,dateOfBirth,fiscalCode," +
                    "documentNumber,address,city,phoneNumber,emailAddress,gender,roleInClinic,login,password," +
                    "badgeNumber,placeOfWork,medicalSpecializzation)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlX,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSurname());
            preparedStatement.setString(3, doctor.getNationality());
            preparedStatement.setString(4, doctor.getPlaceOfBirth());
            preparedStatement.setDate(5, date1);
            preparedStatement.setString(6, doctor.getFiscalCode());
            preparedStatement.setString(7, doctor.getDocumentNumber());
            preparedStatement.setString(8, doctor.getAddress());
            preparedStatement.setString(9, doctor.getCity());
            preparedStatement.setString(10, doctor.getPhoneNumber());
            preparedStatement.setString(11, doctor.getEmailAddress());
            preparedStatement.setString(12, String.valueOf(doctor.getGender()));
            preparedStatement.setString(13, String.valueOf(doctor.getRoleInClinic()));
            preparedStatement.setString(14, doctor.getLogin());
            preparedStatement.setString(15, doctor.getPassword());
            preparedStatement.setString(16, doctor.getBadgeNumber());
            preparedStatement.setString(17, String.valueOf(doctor.getPlaceOfWork()));
            preparedStatement.setString(18, String.valueOf(doctor.getMedicalSpecializzation()));

            preparedStatement.executeUpdate();
            System.out.println("Done!");

            System.out.println("Inserting records into the table 'patient'");
            //prepare String with placeholder
            String sqlPatient = "INSERT INTO patient(name,surname,nationality,placeOfBirth,dateOfBirth,fiscalCode," +
                    "documentNumber,address,city,phoneNumber,emailAddress,gender,medicalPathology)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatementPatient = connection.prepareStatement(sqlPatient,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatementPatient.setString(1, patient.getName());
            preparedStatementPatient.setString(2, patient.getSurname());
            preparedStatementPatient.setString(3, patient.getNationality());
            preparedStatementPatient.setString(4, patient.getPlaceOfBirth());
            preparedStatementPatient.setDate(5, datePatient1);
            preparedStatementPatient.setString(6, patient.getFiscalCode());
            preparedStatementPatient.setString(7, doctor.getDocumentNumber());
            preparedStatementPatient.setString(8, patient.getAddress());
            preparedStatementPatient.setString(9, patient.getCity());
            preparedStatementPatient.setString(10, patient.getPhoneNumber());
            preparedStatementPatient.setString(11, patient.getEmailAddress());
            preparedStatementPatient.setString(12, String.valueOf(patient.getGender()));
            preparedStatementPatient.setString(13, patient.getMedicalPathology());

        preparedStatementPatient.executeUpdate();
            System.out.println("Done!");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

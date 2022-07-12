import lombok.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class MainTest {
    public static void main(String[] args) {

        LocalDate dateOfBirthDoctor = LocalDate.of(1976,10,4);
        LocalDate dateOfBirthDoctor2 = LocalDate.of(1966,1,15);
        LocalDate dateOfBirthSecretary = LocalDate.of(1956,1,5);
        LocalDate dateOfBirthPatient = LocalDate.of(1986,5,24);
        LocalDate dateOfBirthPatient2 = LocalDate.of(1941,1,1);

        Doctor doctor = new Doctor("Bruce","Banner","United States","New York",dateOfBirthDoctor,
                "SDFGHJK615166L","KA452262", "Via Verdi, 14","New York",
                "3336598552","greenforever@gmail.com",Gender.MALE,"greenForever",
                "5pacc4",RoleInClinic.DOCTOR,"BRU456BAN",PlaceOfWork.NAPOLI,
                MedicalSpecializzation.NEUROLOGY);

        Doctor doctor2 = new Doctor("Steven","Strange","United States","New York",dateOfBirthDoctor2,
                "BDGHJL85LK541V","KA459872", "Via degli Arcimboldi, 111","New York",
                "3215455896","dottstrange@gmail.com",Gender.MALE,"thedoctor",
                "magician",RoleInClinic.DOCTOR,"STE654STR",PlaceOfWork.ROMA,
                MedicalSpecializzation.ORTHOPAEDICS);
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
        Secretary secretary = new Secretary("Natasha","Romanoff","Russia","Mosca",dateOfBirthSecretary,
                "ANDMD54369K","gf569874", "Via Tever, 143","Roma",
                "3336598552","3336965698",Gender.MALE,"blackwidow",
                "spider",RoleInClinic.SECRETARY,"NAT546ROM",PlaceOfWork.NAPOLI,
                SecretaryRole.FRONT_OFFICE);

        Patient patient = new Patient("Sara","Connor","United Kindom","London",
                dateOfBirthPatient,"sracnr54de85fa","na456971","Via delle Alpi, 51",
                "Palermo","3256988745","gammaray@yahoo.com",Gender.FEMALE,
                "headache");

        Patient patient2 = new Patient("Steve","Rogers","United States","New York",
                dateOfBirthPatient2,"STVRGS54a54safv5","GA457771","Via degli Scudi, 1",
                "Berlino","3358966541","shield@yahoo.com",Gender.MALE,
                "principle of freezing");

        LocalDate dateOfAppointment = LocalDate.of(2022,7,13);
        LocalTime timeOfAppointment = LocalTime.of(10,30,00);
        Appointment appointment1 = new Appointment(dateOfAppointment,timeOfAppointment,doctor,patient,"Visita generica 1");

        LocalDate dateOfAppointment2 = LocalDate.of(2022,7,13);
        LocalTime timeOfAppointment2 = LocalTime.of(15,30,00);
        Appointment appointment2 = new Appointment(dateOfAppointment,timeOfAppointment,doctor,patient,"Visita generica 2");
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

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it.cartellaClinicaElettronicaConnetedToDB",
                    "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Drop table and view for test
            String query03 = "DROP TABLE IF EXISTS `appointment`;";
            statement.execute(query03);
            String query0 = "DROP TABLE IF EXISTS `doctor`;";
            statement.execute(query0);
            String query01 = "DROP TABLE IF EXISTS `patient`;";
            statement.execute(query01);
            String query02 = "DROP TABLE IF EXISTS `secretary`;";
            statement.execute(query02);
//----------------------------------------------------------------------------------------------------------------------
            //Create a table doctor
            String createTableDoctorQuery = "CREATE TABLE doctor("
                    + "doctorId INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR (20) NOT NULL, "
                    + "surname VARCHAR (20) NOT NULL, "
                    + "nationality VARCHAR (20) NOT NULL, "
                    + "placeOfBirth VARCHAR (20) NOT NULL, "
                    + "dateOfBirth DATE NOT NULL, "
                    + "fiscalCode VARCHAR (16) NOT NULL, "
                    + "documentNumber VARCHAR (10) NOT NULL, "
                    + "address VARCHAR (30) NOT NULL, "
                    + "city VARCHAR (20) NOT NULL, "
                    + "phoneNumber VARCHAR (20) NOT NULL, "
                    + "emailAddress VARCHAR (30) NOT NULL, "
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
//----------------------------------------------------------------------------------------------------------------------
            //Create a table secretary
            String createTableSecretary = "CREATE TABLE secretary("
                    + "secretaryId INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR (20) NOT NULL, "
                    + "surname VARCHAR (20) NOT NULL, "
                    + "nationality VARCHAR (20) NOT NULL, "
                    + "placeOfBirth VARCHAR (20) NOT NULL, "
                    + "dateOfBirth DATE NOT NULL, "
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
                    + "secretaryRole ENUM('FRONT_OFFICE','BACK_OFFICE'), "
                    + "PRIMARY KEY (secretaryId))";
            //Execute query
            statement.execute(createTableSecretary);
            System.out.println("The table 'secretary' was created!");
//----------------------------------------------------------------------------------------------------------------------
            //Create a table patient
            String createTablePatientQuery = "CREATE TABLE patient("
                    + "patientId INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR (20) NOT NULL, "
                    + "surname VARCHAR (20) NOT NULL, "
                    + "nationality VARCHAR (20) NOT NULL, "
                    + "placeOfBirth VARCHAR (20) NOT NULL, "
                    + "dateOfBirth DATE NOT NULL, "
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
//----------------------------------------------------------------------------------------------------------------------
            //Create a table appointment
            String createTableAppointment = "CREATE TABLE appointment("
                    + "appointmentId INT NOT NULL AUTO_INCREMENT, "
                    + "dateOfAppointment DATE NOT NULL, "
                    + "timeOfAppointment TIME NOT NULL, "
                    + "doctorId INT, "
                    + "patientId INT, "
                    + "medicalExaminationReason VARCHAR (20) NOT NULL, "
                    + "PRIMARY KEY (appointmentId), "
                   // + "FOREIGN KEY(doctorId) REFERENCES doctor(doctorId)"
                    + "FOREIGN KEY(patientId) REFERENCES patient(patientId))";
            //Execute query
            statement.execute(createTableAppointment);

            System.out.println("The table 'appointment' was created!");

            String addForeignkeyToAppointment = "ALTER TABLE `appointment` ADD INDEX `doctorId` (`doctorId`)," +
                    " ADD CONSTRAINT `FK_appointment_doctor` FOREIGN KEY (`doctorId`) REFERENCES `doctor` (`doctorId`);";
            statement.execute(addForeignkeyToAppointment);
//----------------------------------------------------------------------------------------------------------------------

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
            preparedStatement.setDate(5, Date.valueOf(dateOfBirthDoctor));
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
            //**********************************************************************************************************************
            int generatedkey=0;
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedkey = rs.getInt(1);
                System.out.println("Auto Generated Primary Key " + generatedkey);
            }
//**********************************************************************************************************************

            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlX,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setString(1, doctor2.getName());
            preparedStatement2.setString(2, doctor2.getSurname());
            preparedStatement2.setString(3, doctor2.getNationality());
            preparedStatement2.setString(4, doctor2.getPlaceOfBirth());
            preparedStatement2.setDate(5, Date.valueOf(dateOfBirthDoctor2));
            preparedStatement2.setString(6, doctor2.getFiscalCode());
            preparedStatement2.setString(7, doctor2.getDocumentNumber());
            preparedStatement2.setString(8, doctor2.getAddress());
            preparedStatement2.setString(9, doctor2.getCity());
            preparedStatement2.setString(10, doctor2.getPhoneNumber());
            preparedStatement2.setString(11, doctor2.getEmailAddress());
            preparedStatement2.setString(12, String.valueOf(doctor2.getGender()));
            preparedStatement2.setString(13, String.valueOf(doctor2.getRoleInClinic()));
            preparedStatement2.setString(14, doctor2.getLogin());
            preparedStatement2.setString(15, doctor2.getPassword());
            preparedStatement2.setString(16, doctor2.getBadgeNumber());
            preparedStatement2.setString(17, String.valueOf(doctor2.getPlaceOfWork()));
            preparedStatement2.setString(18, String.valueOf(doctor2.getMedicalSpecializzation()));

            preparedStatement2.executeUpdate();
            System.out.println("Done!");
//**********************************************************************************************************************
            int generatedkey2 = 0;
            ResultSet rs2 = preparedStatement2.getGeneratedKeys();
            if (rs2.next()) {
                generatedkey2 = rs2.getInt(1);
                System.out.println("Auto Generated Primary Key " + generatedkey2);
            }
//**********************************************************************************************************************
            System.out.println("Inserting records into the table 'secretary'");

            //prepare String with placeholder
            String sqlSecretary = "INSERT INTO secretary(name,surname,nationality,placeOfBirth,dateOfBirth,fiscalCode," +
                    "documentNumber,address,city,phoneNumber,emailAddress,gender,roleInClinic,login,password," +
                    "badgeNumber,placeOfWork,secretaryRole)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatementSecretary = connection.prepareStatement(sqlSecretary,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatementSecretary.setString(1, secretary.getName());
            preparedStatementSecretary.setString(2, secretary.getSurname());
            preparedStatementSecretary.setString(3, secretary.getNationality());
            preparedStatementSecretary.setString(4, secretary.getPlaceOfBirth());
            preparedStatementSecretary.setString(6, secretary.getFiscalCode());
            preparedStatementSecretary.setString(7, secretary.getDocumentNumber());
            preparedStatementSecretary.setDate(5, Date.valueOf(dateOfBirthSecretary));
            preparedStatementSecretary.setString(8, secretary.getAddress());
            preparedStatementSecretary.setString(9, secretary.getCity());
            preparedStatementSecretary.setString(10, secretary.getPhoneNumber());
            preparedStatementSecretary.setString(11, secretary.getEmailAddress());
            preparedStatementSecretary.setString(12, String.valueOf(secretary.getGender()));
            preparedStatementSecretary.setString(13, String.valueOf(secretary.getRoleInClinic()));
            preparedStatementSecretary.setString(14, secretary.getLogin());
            preparedStatementSecretary.setString(15, secretary.getPassword());
            preparedStatementSecretary.setString(16, secretary.getBadgeNumber());
            preparedStatementSecretary.setString(17, String.valueOf(secretary.getPlaceOfWork()));
            preparedStatementSecretary.setString(18, String.valueOf(secretary.getSecretaryRole()));

            preparedStatementSecretary.executeUpdate();
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
            preparedStatementPatient.setDate(5, Date.valueOf(dateOfBirthPatient));
            preparedStatementPatient.setString(6, patient.getFiscalCode());
            preparedStatementPatient.setString(7, patient.getDocumentNumber());
            preparedStatementPatient.setString(8, patient.getAddress());
            preparedStatementPatient.setString(9, patient.getCity());
            preparedStatementPatient.setString(10, patient.getPhoneNumber());
            preparedStatementPatient.setString(11, patient.getEmailAddress());
            preparedStatementPatient.setString(12, String.valueOf(patient.getGender()));
            preparedStatementPatient.setString(13, patient.getMedicalPathology());

        preparedStatementPatient.executeUpdate();
            System.out.println("Done!");

            PreparedStatement preparedStatementPatient2 = connection.prepareStatement(sqlPatient,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatementPatient2.setString(1, patient2.getName());
            preparedStatementPatient2.setString(2, patient2.getSurname());
            preparedStatementPatient2.setString(3, patient2.getNationality());
            preparedStatementPatient2.setString(4, patient2.getPlaceOfBirth());
            preparedStatementPatient2.setDate(5, Date.valueOf(dateOfBirthPatient2));
            preparedStatementPatient2.setString(6, patient2.getFiscalCode());
            preparedStatementPatient2.setString(7, patient2.getDocumentNumber());
            preparedStatementPatient2.setString(8, patient2.getAddress());
            preparedStatementPatient2.setString(9, patient2.getCity());
            preparedStatementPatient2.setString(10, patient2.getPhoneNumber());
            preparedStatementPatient2.setString(11, patient2.getEmailAddress());
            preparedStatementPatient2.setString(12, String.valueOf(patient2.getGender()));
            preparedStatementPatient2.setString(13, patient2.getMedicalPathology());

            preparedStatementPatient2.executeUpdate();
            System.out.println("Done!");
//----------------------------------------------------------------------------------------------------------------------

            System.out.println("Inserting records into the table 'appointment'");
            //prepare String with placeholder
            String sqlAppointment = "INSERT INTO appointment(dateOfAppointment,timeOfAppointment,doctorId,patientId,medicalExaminationReason)"
                    + "VALUES(?,?,?,?,?)";

            PreparedStatement preparedStatementAppointment = connection.prepareStatement(sqlAppointment,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatementAppointment.setDate(1, Date.valueOf(dateOfAppointment));
            preparedStatementAppointment.setTime(2, Time.valueOf(timeOfAppointment));
            preparedStatementAppointment.setInt(3, 1);
            preparedStatementAppointment.setInt(4, 1);
            preparedStatementAppointment.setString(5, appointment1.getMedicalExaminationReason());

            preparedStatementAppointment.executeUpdate();
            System.out.println("Done!");

            PreparedStatement preparedStatementAppointment2 = connection.prepareStatement(sqlAppointment,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatementAppointment2.setDate(1, Date.valueOf(dateOfAppointment2));
            preparedStatementAppointment2.setTime(2, Time.valueOf(timeOfAppointment2));
            preparedStatementAppointment2.setInt(3, 2);
            preparedStatementAppointment2.setInt(4, 1);
            preparedStatementAppointment2.setString(5, appointment2.getMedicalExaminationReason());

            preparedStatementAppointment2.executeUpdate();
            System.out.println("Done!");

//----------------------------------------------------------------------------------------------------------------------

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

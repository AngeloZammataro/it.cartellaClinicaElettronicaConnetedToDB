import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mainFrame extends  JFrame{
    private JLabel labelTitle;
    private JTextField textFieldSurname;
    private JLabel labelSurname;
    private JLabel labelName;
    private JTextField textFieldName;
    private JButton buttonOk;
    private JButton buttonClear;
    private JLabel labelJAVA;
    private JPanel mainPanel;
    private JButton buttonSelectAllNurses;
    private JButton buttonDeleteAllNurses;
    private JButton buttonRebuild;
    private JButton buttonDeleteForId;
    private JTextField textFieldId;
    private JLabel labelId;
    private JLabel labelRegisterNurse;
    private JButton buttonDeleteFromField;
    List<Nurse>nurseList = new ArrayList<>();

    public mainFrame(){
        setContentPane(mainPanel);
        setTitle("Cartella Clinica Elettronica");
        setSize(640,480);
        setLocationRelativeTo(null);
        Image imageIcon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Erlik Khan\\IdeaProjects\\it.cartellaClinicaElettronicaConnetedToDB\\Icon\\icons8-piano-di-trattamento-96.png");
        setIconImage(imageIcon);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String surnName = textFieldSurname.getText();
                labelJAVA.setText("The nurse " + name + " " + surnName  +" has been added");

                //Create a Nurse
                Nurse nurse = new Nurse(name,surnName);
                nurseList.add(nurse);
                System.out.println(nurse.getName() + " - " +  nurse.getSurname());
                System.out.println(nurseList);
                addNurseToDB(nurse.getName(),nurse.getSurname());
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldName.setText("");
                textFieldSurname.setText("");
                labelJAVA.setText("");
                labelId.setText("");
            }
        });
        buttonSelectAllNurses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                String surnName ="";
                getAllNurses();
                for (int i = 0; i < nurseList.size(); i++) {
                    name = textFieldName.getText();
                    surnName = textFieldSurname.getText();
                    labelJAVA.setText(name + " " + surnName);
                }
            }
        });
        buttonDeleteAllNurses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllNurses();
                labelJAVA.setText("All nurses have been deleted");
            }
        });
        buttonRebuild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rebuildAllNurses();
                labelJAVA.setText("All nurses have been rebuilded");
            }
        });
        buttonDeleteForId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idFromDb = Integer.parseInt(textFieldId.getText());
                deleteNursesForId(idFromDb);
                labelJAVA.setText("Nurse with id " + idFromDb + " has been deleted");
            }
        });
        buttonDeleteFromField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String surnname = textFieldSurname.getText();
                System.out.println(name + " " + surnname);
                deleteNursesForNameAndSurname(name,surnname);
                labelJAVA.setText("Nurse " + name + " " + surnname + " has been deleted");
            }
        });
    }

        public void addNurseToDB(String nome, String cognome){
            try {
                //Create a connection to database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                        "root", "S1V1sP4c3mP4r4B3llum");
                Statement statement = connection.createStatement();

                //Create a table doctor
                String createTableNurse = "CREATE TABLE IF NOT EXISTS nurse("
                        + "nurseId INT NOT NULL AUTO_INCREMENT, "
                        + "name VARCHAR (20), "
                        + "surname VARCHAR (20), "
                        + "PRIMARY KEY (nurseId))";
                //Execute query
                statement.execute(createTableNurse);
                System.out.println("The table 'nurse' was created!");

//----------------------------------------------------------------------------------------------------------------------
                System.out.println("Registering a new 'nurse'...");

                //prepare String with placeholder
                String sqlNurse = "INSERT INTO nurse(name,surname)"
                        + "VALUES(?,?)";

                PreparedStatement preparedStatementNurse = connection.prepareStatement(sqlNurse,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatementNurse.setString(1,nome);
                preparedStatementNurse.setString(2,cognome);

/*
                for (int i = 0; i < nurseList.size(); i++) {
                    preparedStatementNurse.setString(3, String.valueOf(nurseList.get(i)));
                }
*/
                preparedStatementNurse.executeUpdate();
                System.out.println("A new nurse was registered");

                connection.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

//----------------------------------------------------------------------------------------------------------------------
    public void getAllNurses(){

        try {
        //Create a connection to database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                "root", "S1V1sP4c3mP4r4B3llum");
        Statement statement = connection.createStatement();

        //Create a select
        ResultSet selectAllNurses = statement.executeQuery("SELECT * FROM nurse");
        System.out.println("SELECT all FROM nurse");
        while (selectAllNurses.next()){
            System.out.println(selectAllNurses.getString("name")+ " - " +
                               selectAllNurses.getString("surname"));
        }
        System.out.println("Done!");

            connection.close();

    }catch (SQLException e){
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    public void deleteAllNurses(){

        try {
            //Create a connection to database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                    "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Create a delete
            String deleteAllNurses = "UPDATE nurse " +
                    "SET status = 'DELETED' ";
            statement.executeUpdate(deleteAllNurses);
            System.out.println("SET nurse to status DELETED");
            System.out.println("Done!");

            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public void deleteNursesForId(int id){

        try {
            //Create a connection to database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                    "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Create a delete
            String deleteNursesById = "UPDATE nurse " +
                    "SET status = 'DELETED' WHERE nurseId = " + id;
            statement.executeUpdate(deleteNursesById);
            System.out.println("SET nurse " + id + " to status DELETED");
            System.out.println("Done!");

            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public void deleteNursesForNameAndSurname(String name, String surname){

        try {
            //Create a connection to database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                    "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Create a delete
            String deleteNursesByField = "UPDATE nurse " +
                    "SET status = 'DELETED' WHERE surname = '" + surname + "'AND name = '" + name + "'";
            statement.executeUpdate(deleteNursesByField);
            System.out.println("SET nurse " + name + " " + surname + " to status DELETED");
            System.out.println("Done!");

            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public void rebuildAllNurses(){

        try {
            //Create a connection to database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingGuiTest",
                    "root", "S1V1sP4c3mP4r4B3llum");
            Statement statement = connection.createStatement();

            //Create a delete
            String rebuildAllNurses = "UPDATE nurse " +
                    "SET status = 'ACTIVE' ";
            statement.executeUpdate(rebuildAllNurses);
            System.out.println("SET nurse to status ACTIVE");
            System.out.println("Done!");

            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        mainFrame mainFrame = new mainFrame();
    }
}

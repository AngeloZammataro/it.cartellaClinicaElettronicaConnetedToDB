import javax.swing.*;
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
    List<Nurse>nurseList = new ArrayList<>();
    List<Nurse>AllNurseFromDb = new ArrayList<>();

    public mainFrame(){
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String surnName = textFieldSurname.getText();
                labelJAVA.setText("Welcome " + name + " " + surnName);

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

    public static void main(String[] args) {
        mainFrame mainFrame = new mainFrame();
    }
}

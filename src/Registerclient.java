import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registerclient extends JFrame{

    private JTextField FNameEntry, SNameEntry,EmailAddressEntry,PhoneNumberEntry,GenderEntry;
    private JLabel FNamelbl, SNamelbl,EmailAddresslbl,PhoneNumberlbl,Genderlbl;
    private JButton Submitbtn,Viewbtn;


    public Registerclient(){
        setTitle("Client Registration");
        setLayout(new GridLayout(6,2));
        setSize(400,400);

        FNameEntry = new JTextField();
        SNameEntry = new JTextField();
        GenderEntry = new JTextField();
        PhoneNumberEntry = new JTextField();
        EmailAddressEntry = new JTextField();

        FNamelbl = new JLabel("First name");
        SNamelbl = new JLabel("Second name");
        Genderlbl = new JLabel("Gender");
        PhoneNumberlbl = new JLabel("Phone number");
        EmailAddresslbl = new JLabel("Email addresss");

        Submitbtn = new JButton("Submit ");
        Viewbtn= new JButton("View clients");

        add(FNamelbl);
        add(FNameEntry);
        add(SNamelbl);
        add(SNameEntry);
        add(Genderlbl);
        add(GenderEntry);
        add(PhoneNumberlbl);
        add(PhoneNumberEntry);
        add(EmailAddresslbl);
        add(EmailAddressEntry);
        add(Submitbtn);
        add(Viewbtn);

        Submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerClientDatabase();
            }
        });

        Viewbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Viewclients view = new Viewclients();
            }
        });
        setVisible(true);
    }
    private void registerClientDatabase()
    {
        String fName = FNameEntry.getText().trim();
        String sName = SNameEntry.getText().trim();
        String gender = GenderEntry.getText().trim();
        String phoneNo = PhoneNumberEntry.getText().trim();
        String emailAddress = EmailAddressEntry.getText().trim();

        if (!(fName.isEmpty() || fName == null) && !(sName.isEmpty() || sName == null)
                && !(gender.isEmpty() || gender == null) && !(phoneNo.isEmpty() || phoneNo == null)
                && !(emailAddress.isEmpty() || emailAddress == null))
        {
            String URL = "jdbc:mysql://localhost:3306/booking_hotel";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection connection = null;
            PreparedStatement ps = null;
            try {

                boolean success = false;
                connection = DriverManager.getConnection(URL, USER, PASSWORD);  // Create connection to database
                String sql = "INSERT INTO Client (FName, SName, Email_address, Phonenumber, Gender) " +
                        "VALUES (?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);

                ps.setString(1, fName);
                ps.setString(2, sName);
                ps.setString(3, emailAddress);
                ps.setString(4, phoneNo);
                ps.setString(5, gender);

                ps.executeUpdate();
                success = true;

                if (success)
                {
                    JOptionPane.showMessageDialog(null, "Client Registration Successful");
                    FNameEntry.setText("");
                    SNameEntry.setText("");
                    GenderEntry.setText("");
                    PhoneNumberEntry.setText("");
                    EmailAddressEntry.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Client Registration Failed");
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();  // Print error message and stack trace
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
        }

    }
}

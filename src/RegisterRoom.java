import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterRoom extends JFrame{
    private JTextField capacityEntry, descriptionEntry, priceEntry;
    private JLabel capacityLbl, descriptionLbl, priceLbl;
    private JButton Submitbtn,Viewbtn;


    public RegisterRoom(){
        setTitle("Room Registration");
        setLayout(new GridLayout(4,2));
        setSize(400,180);

        capacityEntry = new JTextField();
        priceEntry = new JTextField();
        descriptionEntry = new JTextField();


        capacityLbl = new JLabel("Room Capacity");
        priceLbl = new JLabel("Price Per Day");
        descriptionLbl = new JLabel("Room Description");

        Submitbtn = new JButton("Submit ");
        Viewbtn= new JButton("View Rooms");

        add(descriptionLbl);
        add(descriptionEntry);

        add(capacityLbl);
        add(capacityEntry);

        add(priceLbl);
        add(priceEntry);

        add(Submitbtn);
        add(Viewbtn);

        Submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerRoomDatabase();
            }
        });

        Viewbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRoom room = new ViewRoom();
            }
        });
        setVisible(true);
    }
    private void registerRoomDatabase()
    {
        String capacity = capacityEntry.getText().trim();
        String description = descriptionEntry.getText().trim();
        String price = priceEntry.getText().trim();

        if (!(capacity.isEmpty() || capacity == null) && !(description.isEmpty() || description == null)
                && !(price.isEmpty() || price == null))
        {
            String URL = "jdbc:mysql://localhost:3306/booking_hotel";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection connection = null;
            PreparedStatement ps = null;
            try {

                boolean success = false;
                connection = DriverManager.getConnection(URL, USER, PASSWORD);  // Create connection to database
                String sql = "INSERT INTO Room(Capacity, Description, Price) " +
                        "VALUES (?, ?, ?)";
                ps = connection.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(capacityEntry.getText()));
                ps.setString(2, descriptionEntry.getText());
                ps.setInt(3, Integer.parseInt(priceEntry.getText()));

                ps.executeUpdate();
                success = true;

                if (success)
                {
                    JOptionPane.showMessageDialog(null, "Room Registration Successful");

                    capacityEntry.setText("");
                    descriptionEntry.setText("");
                    priceEntry.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Room Registration Failed");
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

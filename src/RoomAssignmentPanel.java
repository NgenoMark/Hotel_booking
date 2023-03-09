import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class RoomAssignmentPanel extends JPanel {

    private JTextField roomEntry, dateEntry, statusEntry, paymentEntry, daysEntry, roomId;
    private JLabel roomlbl, datelbl, statuslbl, paymentlbl, dayslbl;
    private JButton Submitbtn;
    private CustomTable dataTable;
    private int customerId;
    public RoomAssignmentPanel(CustomTable table, int customerId){
        setLayout(new GridLayout(6,2));
        dataTable = table;

        dateEntry = new JTextField();
        daysEntry = new JTextField();
        paymentEntry = new JTextField();
        statusEntry = new JTextField();
        roomEntry = new JTextField();
        roomId = new JTextField();

        roomId.setEditable(false);
        roomEntry.setEditable(false);

        datelbl = new JLabel("Date");
        dayslbl = new JLabel("Number of Days");
        paymentlbl = new JLabel("Payment Method");
        statuslbl = new JLabel("Status");
        roomlbl = new JLabel("Room Description");

        Submitbtn = new JButton("Submit ");

        add(roomlbl);
        add(roomEntry);

        add(datelbl);
        add(dateEntry);

        add(dayslbl);
        add(daysEntry);

        add(paymentlbl);
        add(paymentEntry);

        add(statuslbl);
        add(statusEntry);

        add(Submitbtn);

        Submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomAssignmentDatabase();
            }
        });

        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomTable target = (CustomTable) e.getSource();
                int row = target.getSelectedRow();
                String id = Integer.toString((Integer) target.getValueAt(row, 0));
                String description = (String) target.getValueAt(row, 1);

                if (e.getClickCount() == 2) {
                    roomId.setText(id);
                    roomEntry.setText(description);
                }
            }
        });

        setVisible(true);
    }
    private void roomAssignmentDatabase()
    {
        String date = dateEntry.getText();
        String days = daysEntry.getText();
        String payment = paymentEntry.getText();
        String status = statusEntry.getText();
        String roomID = roomId.getText();

        if (!(date.isEmpty() || date == null) && !(days.isEmpty() || days == null)
                && !(payment.isEmpty() || payment == null) && !(status.isEmpty() || status == null)
                && !(roomID.isEmpty() || roomID == null))
        {
            String URL = "jdbc:mysql://localhost:3306/booking_hotel";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection connection = null;
            PreparedStatement ps = null;
            try {
                boolean success = false;
                connection = DriverManager.getConnection(URL, USER, PASSWORD);  // Create connection to database
                String sql = "INSERT INTO room_assignment(client_id, Number_of_days, Payment_method, room_id, " +
                        "Start_date, Status_of_room) VALUES (?, ?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);

                ps.setInt(1,1);
                ps.setInt(2, Integer.parseInt(daysEntry.getText()));
                ps.setString(3, paymentEntry.getText());
                ps.setInt(4, Integer.parseInt(roomId.getText()));
                ps.setDate(5, Date.valueOf(dateEntry.getText()));
                ps.setString(6, statusEntry.getText());

                ps.executeUpdate();
                success = true;

                if (success)
                {
                    JOptionPane.showMessageDialog(null, "Room Assignment Successful");
                    dateEntry.setText("");
                    daysEntry.setText("");
                    paymentEntry.setText("");
                    statusEntry.setText("");
                    roomEntry.setText("");
                    roomId.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Room Assignment Failed");
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

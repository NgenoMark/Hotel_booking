import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JButton registerClientBtn, registerRoomBtn, viewClientBtn, viewRoomBtn, viewRoomAssignmentBtn;

    Dashboard() {
        setTitle("Room Booking");
        setLayout(new GridLayout(5, 1));
        setSize(600, 350);

        registerClientBtn = new JButton("Register Client");
        registerRoomBtn = new JButton("Register Room");
        viewRoomBtn = new JButton("View Rooms");
        viewRoomAssignmentBtn = new JButton("Room Assignments");
        viewClientBtn = new JButton("View Clients");

        add(registerClientBtn);
        add(registerRoomBtn);
        add(viewClientBtn);
        add(viewRoomBtn);
        add(viewRoomAssignmentBtn);

        registerRoomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterRoom room = new RegisterRoom();
            }
        });

        registerClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registerclient rc  = new Registerclient();
            }
        });
        setVisible(true);

        viewRoomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRoom room = new ViewRoom();
            }
        });

        viewClientBtn.addActionListener(new ActionListener() {
            private Label userField;
            private JPasswordField passwordField;


            @Override
            public void actionPerformed(ActionEvent e) {

                LoginFrame frame = new LoginFrame();
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("admin") && password.equals("password")) {
                    new Viewclients();
                    dispose();
                } else {
                    System.out.println("Invalid Username or Password ");
                }
                Viewclients clients = new Viewclients();
            }
        });

        viewRoomAssignmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignmentSummary summary = new AssignmentSummary();
            }
        });
    }





    public static void main(String[] args)
    {
        Dashboard dashboard = new Dashboard();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {
    private JLabel userLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Grocery Store Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = new String(passwordField.getPassword());
        if (username.equals("admin") && password.equals("password")) {
            new Viewclients();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
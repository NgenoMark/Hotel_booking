import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Viewclients extends JFrame{

    private JButton searchButton;
    private JTextField searchField;
    private CustomTable dataTable;

    public Viewclients() {

        super("View Clients");

        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Create the table
        dataTable = new CustomTable();
        populateTable(" SELECT * FROM Client");

        // Set up the layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Add the components to the layout
        JPanel searchPanel = new JPanel(new GridLayout(1, 2));

        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        pack();
        setVisible(true);

        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomTable target = (CustomTable) e.getSource();
                int row = target.getSelectedRow();
                int id = (int) target.getValueAt(row, 0);

                if (e.getClickCount() == 2) {
                    RoomAssignment room = new RoomAssignment(id);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchClient();
            }
        });

        setVisible(true);
    }

    private void searchClient()
    {
        String search = searchField.getText();
        String sql = "SELECT * FROM Client WHERE ClientID LIKE '%" + search + "%' OR " +
                "FName LIKE '%" + search + "%' OR SName LIKE '%" + search + "%' OR " +
                "Email_address LIKE '%"+ search +"%' OR Phonenumber LIKE '%" +
                search + "%' OR Gender LIKE '%" + search + "%'";
        populateTable(sql);
    }

    private void populateTable(String sql)
    {
        try {
            String URL = "jdbc:mysql://localhost:3306/booking_hotel";
            String USER = "root";  // Define database credentials
            String PASSWORD = "";

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            int numCols = rs.getMetaData().getColumnCount();

            // Create a DefaultTableModel with the correct number of columns
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 1; i <= numCols; i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Add the data to the model
            while (rs.next()) {
                Object[] rowData = new Object[numCols];
                for (int i = 1; i <= numCols; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
            dataTable.setModel(model);

            con.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}

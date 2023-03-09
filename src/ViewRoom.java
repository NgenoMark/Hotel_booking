import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewRoom extends JFrame{
    public ViewRoom()
    {
        setTitle("View Rooms");
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridLayout(1, 1));

        ViewRoomPanel roomPanel = new ViewRoomPanel();
        add(roomPanel);

        pack();
        setVisible(true);
    }
}
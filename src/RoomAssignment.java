import javax.swing.*;
import java.awt.*;

public class RoomAssignment extends JFrame {
    private JPanel left;
    private ViewRoomPanel right;
    RoomAssignment(int id)
    {
        setTitle("Room Assignment");
        setLayout(new GridLayout(1, 2));
        setSize(800, 350);

        right = new ViewRoomPanel();
        left = new RoomAssignmentPanel(right.getCustomTable(), id);

        add(left);
        add(right);

        setVisible(true);
    }
}
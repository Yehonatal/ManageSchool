import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    public AdminPanel() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Add additional components and functionality for the admin panel as needed.
    }
}

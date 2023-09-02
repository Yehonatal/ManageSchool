import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JPanel currentPanel;

    public App() {
        setTitle("ManageSchool");
        setSize(950, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initially, show the login panel
        currentPanel = new LoginPanel(this);
        add(currentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void switchPanel(JPanel newPanel) {
        remove(currentPanel);
        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}
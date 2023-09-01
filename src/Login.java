import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox adminCheckBox;
    private JCheckBox studentCheckBox;
    private JButton loginButton;

    public Login() {
        setTitle("Login Page");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with GridBagLayout for centering
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        centerPanel.add(new JLabel("Username:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        centerPanel.add(usernameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        centerPanel.add(new JLabel("Password:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        centerPanel.add(passwordField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        adminCheckBox = new JCheckBox("Admin");
        studentCheckBox = new JCheckBox("Student");
        JPanel userTypePanel = new JPanel();
        userTypePanel.add(adminCheckBox);
        userTypePanel.add(studentCheckBox);
        centerPanel.add(userTypePanel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        loginButton = new JButton("Login");
        centerPanel.add(loginButton, gridBagConstraints);

        // Add the center panel to the content pane
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                StringBuilder userType = new StringBuilder();

                if (adminCheckBox.isSelected()) {
                    userType.append("admin ");
                }

                if (studentCheckBox.isSelected()) {
                    userType.append("student ");
                }

                if (userType.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select at least one user type.");
                } else {
                    DatabaseAccess access = new DatabaseAccess("select", userType.toString().trim(), username, password);
                    String result = access.dbAccessor();

                    if (result.equals("true")) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed. Please check your credentials.");
                    }
                }
            }
        });
    }

}
               

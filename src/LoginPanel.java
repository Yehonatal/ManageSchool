import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox adminCheckBox;
    private JCheckBox studentCheckBox;
    private JButton loginButton;
    private App parentApp;

    String username;
    char[] passwordChars;
    String password;
    StringBuilder userType;

 
    public LoginPanel(App app) {
        parentApp = app;

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(new JLabel("Username:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        add(usernameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        add(new JLabel("Password:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        add(passwordField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        adminCheckBox = new JCheckBox("Admin");
        studentCheckBox = new JCheckBox("Student");
        JPanel userTypePanel = new JPanel();
        userTypePanel.add(adminCheckBox);
        userTypePanel.add(studentCheckBox);
        add(userTypePanel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        loginButton = new JButton("Login");
        add(loginButton, gridBagConstraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                passwordChars = passwordField.getPassword();
                password = new String(passwordChars);
                userType = new StringBuilder();

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

                        // Switch to the appropriate panel based on user type
                        if (adminCheckBox.isSelected()) {
                            parentApp.switchPanel(new AdminPanel(parentApp));
                        } else if (studentCheckBox.isSelected()) {
                            parentApp.switchPanel(new StudentPanel(parentApp, password));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed. Please check your credentials.");
                    }
                }
            }
        });
    }
}
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class App extends JFrame {
    JButton logBtn;
    JTextField chooser;
    JTextField usrField;
    JTextField pswField;
    JPanel panel;

    public static String userType;
    public static String userName;
    public static String password;

    public App(){
        setTitle("ManageSchool");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chooser = new JTextField(30);
        usrField = new JTextField(30);
        pswField = new JTextField(30);
        logBtn = new JButton("Log In");

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.insets = new Insets(6, 6, 6, 6);

        con.gridx = 0;
        con.gridy = 0;
        panel.add(chooser, con);
        con.gridy = 1;
        panel.add(usrField, con);
        con.gridy = 2;
        panel.add(pswField, con);
        con.gridy = 3;
        panel.add(logBtn, con);

        add(panel);

        logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = chooser.getText();
                userName = usrField.getText();
                password = pswField.getText();

                createLogin();
            }
        });
    }
    
    public void createLogin() {
        DatabaseAccess access = new DatabaseAccess("select", userType, userName, password);
        String check = access.dbAccessor();
        System.out.println(check);

    }


    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });

       

    }
}

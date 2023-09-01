import javax.swing.*;
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}
               

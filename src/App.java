import java.sql.*;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // Check if db connection works
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/manageschool", "sqluser", "password");
            Statement statement = connection.createStatement();

            String select = "SELECT * FROM studentLog";
            ResultSet rs = statement.executeQuery(select);

            while (rs.next()) {
                String name = rs.getString("studentName");
                System.out.println(name);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

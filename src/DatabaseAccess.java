import java.sql.*;

public class DatabaseAccess {
    private String accessType;
    private String userType;
    private Statement statement;
    private String userName;
    private String password;

    public DatabaseAccess(String accessType, String userType, String userName, String userPsw) {
        this.accessType = accessType.toLowerCase();
        this.userType = userType.toLowerCase();
        this.userName = userName.toLowerCase();
        this.password = userPsw.toLowerCase();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manageschool", "sqluser", "password");
            statement = conn.createStatement();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public String dbAccessor() {
        switch (userType) {
            case "admin":
                if (accessType.equals("select")) {
                    return selectData("adminUserName", "adminUserPsw", "adminLog");
                }
                break;

            case "student":
                if (accessType.equals("select")) {
                    return selectData("studentName", "studentId", "studentLog");
                }
                break;
        }
        return "false case 1";
    }

    public String selectData(String user, String psw, String table) {
        ResultSet result;
        try {
            result = statement.executeQuery(String.format("SELECT * FROM %s", table));
            while (result.next()) {
                String usr = result.getString(user);
                String pswd = result.getString(psw);
                if (usr.equals(userName) && pswd.equals(password)) {
                    return "true";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false";
    }
}

import java.sql.*;

public class DatabaseAccess {
    private String accessType;
    private String userType;
    private Connection conn;
    private String userName;
    private String password;

    public DatabaseAccess(String accessType, String userType, String userName, String userPsw) {
        this.accessType = accessType.toLowerCase();
        this.userType = userType.toLowerCase();
        this.userName = userName.toLowerCase();
        this.password = userPsw.toLowerCase();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manageschool", "sqluser", "password");
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
        return "false";
    }

    public String selectData(String user, String psw, String table) {
        ResultSet result;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", table, user, psw)
            );
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                return "true";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false";
    }

}

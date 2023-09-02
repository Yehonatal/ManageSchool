import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentPanel extends JPanel {
    private App parentApp;
    private JTabbedPane tabbedPane;
    Connection con;
    Statement statement;
    String studId;

    // Components for enrolling in courses
    private JTable availableCoursesTable;
    private JTextField courseCodeField;
    private JButton enrollButton;

    // Component for viewing enrolled courses and grades
    private JTable enrolledCoursesTable;

    public StudentPanel(App app, String psw) {
        this.studId = psw;
        parentApp = app;
        setupUI();
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(titleLabel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        setupEnrollToCourse();
        setupViewGrades();

        add(tabbedPane, BorderLayout.CENTER);
        add(createLogoutPanel(), BorderLayout.SOUTH);
    }

    private void setupEnrollToCourse() {
        JPanel enrollToCoursePanel = new JPanel(new BorderLayout());
        DefaultTableModel availableCoursesTableModel = new DefaultTableModel();
        availableCoursesTableModel.addColumn("Course ID");
        availableCoursesTableModel.addColumn("Course Name");
        availableCoursesTableModel.addColumn("Credit");
        availableCoursesTable = new JTable(availableCoursesTableModel);
        JScrollPane availableCoursesScrollPane = new JScrollPane(availableCoursesTable);
        enrollToCoursePanel.add(availableCoursesScrollPane, BorderLayout.CENTER);

        availableCoursesTable.setDefaultEditor(Object.class, null);

        JPanel enrollFormPanel = new JPanel(new FlowLayout());
        courseCodeField = new JTextField(20);
        enrollButton = createButton("Enroll", e -> enrollInCourse());
        enrollFormPanel.add(new JLabel("Enter Course Code:"));
        enrollFormPanel.add(courseCodeField);
        enrollFormPanel.add(enrollButton);
        enrollToCoursePanel.add(enrollFormPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Enroll to Course", null, enrollToCoursePanel, "Enroll in courses");

        // Populate available courses
        populateAvailableCoursesTable();
    }

    private void setupViewGrades() {
        JPanel viewGradesPanel = new JPanel(new BorderLayout());

        // Create a table to display enrolled courses and grades
        DefaultTableModel enrolledCoursesTableModel = new DefaultTableModel();
        enrolledCoursesTableModel.addColumn("Student Name");
        enrolledCoursesTableModel.addColumn("Student ID");
        enrolledCoursesTableModel.addColumn("Student Status");
        enrolledCoursesTableModel.addColumn("Course Code");
        enrolledCoursesTableModel.addColumn("Grade");
        enrolledCoursesTable = new JTable(enrolledCoursesTableModel);
        JScrollPane enrolledCoursesScrollPane = new JScrollPane(enrolledCoursesTable);
        viewGradesPanel.add(enrolledCoursesScrollPane, BorderLayout.CENTER);

        enrolledCoursesTable.setDefaultEditor(Object.class, null);


        tabbedPane.addTab("View Your Grades", null, viewGradesPanel, "View your grades");

        // Populate enrolled courses and grades
        populateEnrolledCoursesTable();
    }

    private Component createLogoutPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = createButton("Logout", e -> parentApp.switchPanel(new LoginPanel(parentApp)));
        logoutButton.setPreferredSize(new Dimension(80, 25));
        buttonPanel.add(logoutButton);
        return buttonPanel;
    }

    private JButton createButton(String string, ActionListener actionListener) {
        JButton button = new JButton(string);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(actionListener);
        return button;
    }

    private void tablePopulate() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manageschool", "sqluser", "password");
            }
            if (statement == null || statement.isClosed()) {
                statement = con.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void populateAvailableCoursesTable() {
        tablePopulate();
        try {
            String grabber = "SELECT * FROM courseslog";
            ResultSet resultSet = statement.executeQuery(grabber);

            while (resultSet.next()) {
                String courseId = resultSet.getString("courseId");
                String courseTitle = resultSet.getString("courseTitle");
                String courseCredit = resultSet.getString("courseCredit");
                DefaultTableModel model = (DefaultTableModel) availableCoursesTable.getModel();
                model.addRow(new Object[] {courseId, courseTitle, courseCredit});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    private void populateEnrolledCoursesTable() {
        tablePopulate();
        DefaultTableModel studentsEnrolledModel = (DefaultTableModel) enrolledCoursesTable.getModel();
        studentsEnrolledModel.setRowCount(0);
        try {
            String grabber = String.format("select studentlog.studentName, studentlog.studentId, studentlog.studentStatus, enrollments.course_id, enrollments.grade from studentlog inner join enrollments on studentlog.studentId = enrollments.student_id where studentlog.studentId = " + "'%S'", studId) ;
            ResultSet resultSet = statement.executeQuery(grabber);
            while (resultSet.next()) {
                String studentName = resultSet.getString("studentName");
                String studentId = resultSet.getString("studentId");
                String studentStatus = resultSet.getString("studentStatus");
                String courseId = resultSet.getString("course_id");
                String grade = resultSet.getString("grade");
                
                studentsEnrolledModel.addRow(new Object[] {studentId, studentName, studentStatus, courseId, grade});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enrollInCourse() {
        String courseCode = courseCodeField.getText();
        tablePopulate();
        try {
            String adder = String.format("INSERT INTO enrollments(student_id, course_id) VALUES('%s', '%s')", studId, courseCode);     
            statement.executeUpdate(adder);       
            JOptionPane.showMessageDialog(this, "Successfully enrolled ...  ", "Update complete", JOptionPane.INFORMATION_MESSAGE);
            populateEnrolledCoursesTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error enrolling data to the database", "Database Error", JOptionPane.ERROR_MESSAGE);
        } 
    }

 
}

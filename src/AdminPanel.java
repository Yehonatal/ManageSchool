import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminPanel extends JPanel {
    private App parentApp;
    private JTabbedPane tabbedPane;
    Connection con;
    Statement statement;

    // Components for adding students
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JCheckBox studentStatusCheckBox;

    // Components for creating courses
    private JTable availableCoursesTable;
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JTextField courseCreditField;

    // Components for assigning grades
    private JTextField assignStudentIdField;
    private JTextField assignCourseIdField;
    private JTextField gradeField;

    public AdminPanel(App app) {
        parentApp = app;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(titleLabel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        setupAddStudentTab();
        setupCreateCoursesTab();
        setupAssignGradesTab();

        add(tabbedPane, BorderLayout.CENTER);
        add(createLogoutPanel(), BorderLayout.SOUTH);
    }

    private void setupAddStudentTab() {
        JPanel addStudentPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Add Students", null, addStudentPanel, "Add Students to Database");

        studentNameField = createTextField(20);
        studentIdField = createTextField(20);
        studentStatusCheckBox = new JCheckBox("Active");
        JButton addStudentButton = createButton("Add Student", e -> addStudent());

        addComponentsToPanel(addStudentPanel, studentNameField, "Student Name:", 0, 0);
        addComponentsToPanel(addStudentPanel, studentIdField, "Student ID:", 0, 1);
        addComponentsToPanel(addStudentPanel, studentStatusCheckBox, "Student Status:", 0, 2);
        addComponentsToPanel(addStudentPanel, addStudentButton, 1, 3);
    }

    private void setupCreateCoursesTab() {
        JPanel createCoursePanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Create Courses", null, createCoursePanel, "Create Courses");

        DefaultTableModel availableCoursesTableModel = new DefaultTableModel();
        availableCoursesTableModel.addColumn("Course ID");
        availableCoursesTableModel.addColumn("Course Name");
        availableCoursesTableModel.addColumn("Credit");
        availableCoursesTable = new JTable(availableCoursesTableModel);
        JScrollPane availableCoursesScrollPane = new JScrollPane(availableCoursesTable);

        availableCoursesTable.setDefaultEditor(Object.class, null);

        // Populate available courses
        populateAvailableCoursesTable();

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(availableCoursesScrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(1.0); 

        splitPane.setTopComponent(tablePanel); 
        splitPane.setBottomComponent(createCourseFormPanel()); 
        createCoursePanel.add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createCourseFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());

        courseNameField = createTextField(20);
        courseCodeField = createTextField(20);
        courseCreditField = createTextField(20);
        JButton createCourseButton = createButton("Create Course", e -> createCourse());

        addComponentsToPanel(formPanel, courseNameField, "Course Title:", 0, 0);
        addComponentsToPanel(formPanel, courseCodeField, "Course Code:", 0, 1);
        addComponentsToPanel(formPanel, courseCreditField, "Course Credit:", 0, 2);
        addComponentsToPanel(formPanel, createCourseButton, 0, 3);

        return formPanel;
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
            JOptionPane.showMessageDialog(this, "Error fetching data from the database", "Database Error", JOptionPane.ERROR_MESSAGE);
        } 
    }

    private void setupAssignGradesTab() {
        JPanel assignGradesPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Assign Grades", null, assignGradesPanel, "Assign Grades to Students");

        assignStudentIdField = createTextField(20);
        assignCourseIdField = createTextField(20);
        gradeField = createTextField(5);
        JButton assignGradeButton = createButton("Assign Grade", e -> assignGrade());

        addComponentsToPanel(assignGradesPanel, assignStudentIdField, "Select Student:", 0, 0);
        addComponentsToPanel(assignGradesPanel, assignCourseIdField, "Select Course:", 0, 1);
        addComponentsToPanel(assignGradesPanel, gradeField, "Enter Grade:", 0, 2);
        addComponentsToPanel(assignGradesPanel, assignGradeButton, 1, 3);
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        return textField;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.addActionListener(actionListener);
        return button;
    }

    private void addComponentsToPanel(JPanel panel, Component component, int gridx, int gridy) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = 1;

        if (component instanceof JButton) {
            panel.add((JButton) component, gridBagConstraints);
        }
    }

    private void addComponentsToPanel(JPanel panel, Component component, String labelText, int gridx, int gridy) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.insets = new Insets(5, 5, 5, 5);
        labelConstraints.gridx = gridx;
        labelConstraints.gridy = gridy;
        labelConstraints.anchor = GridBagConstraints.WEST;

        GridBagConstraints componentConstraints = new GridBagConstraints();
        componentConstraints.insets = new Insets(5, 5, 5, 5);
        componentConstraints.gridx = gridx + 1;
        componentConstraints.gridy = gridy;
        componentConstraints.fill = GridBagConstraints.HORIZONTAL;
        componentConstraints.anchor = GridBagConstraints.WEST;

        if (component instanceof JScrollPane) {
            panel.add(component, componentConstraints);
        } else if (component instanceof JTextField || component instanceof JCheckBox) {
            panel.add(new JLabel(labelText), labelConstraints);
            panel.add(component, componentConstraints);
        } else if (component instanceof JButton) {
            componentConstraints.gridwidth = 2;
            panel.add(component, componentConstraints);
        }
    }

    private JPanel createLogoutPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = createButton("Logout", e -> parentApp.switchPanel(new LoginPanel(parentApp)));
        logoutButton.setPreferredSize(new Dimension(80, 25));
        buttonPanel.add(logoutButton);
        return buttonPanel;
    }

    private void addStudent() {
        // Retrieve student information and add to the database
        String studentName = studentNameField.getText();
        String studentId = studentIdField.getText();
        Boolean studentActiveStatus = studentStatusCheckBox.isSelected();

        // TODO: Add code to insert the student data into the StudentLog table
        // Clear the form fields after adding the student
        clearFormFields(studentNameField, studentIdField, studentStatusCheckBox);
    }

    private void createCourse() {
        // Retrieve course information and add to the database
        String courseName = courseNameField.getText();
        String courseCode = courseCodeField.getText();
        String courseCredit = courseCreditField.getText();

        // TODO: Add code to insert the course data into the CoursesLog table
        // Clear the form fields after creating the course
        clearFormFields(courseNameField, courseCodeField, courseCreditField);
    }

    private void assignGrade() {
        // Retrieve selected student, course, and grade and update the database
        String selectedStudent = assignStudentIdField.getText();
        String selectedCourse = assignCourseIdField.getText();
        String grade = gradeField.getText();

        // TODO: Add code to update the corresponding course table with the assigned grade
        // Clear the form fields after assigning the grade
        clearFormFields(assignStudentIdField, assignCourseIdField, gradeField);
    }

    private void clearFormFields(Component... components) {
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private App parentApp;
    private JTabbedPane tabbedPane;

    // Components for adding students
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JCheckBox studentStatusCheckBox;

    // Components for creating courses
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JTextField courseCreditField;

    // Components for assigning grades
    private JTextField assignStudentId;
    private JTextField assignCourseId;
    private JTextField gradeField;

    public AdminPanel(App app) {
        parentApp = app;

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(titleLabel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();

        // Tab for adding students
        JPanel addStudentPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Add Students", null, addStudentPanel, "Add Students to Database");

        // Components for adding students
        studentNameField = new JTextField(20);
        studentIdField = new JTextField(20);
        studentStatusCheckBox = new JCheckBox("Active");
        JButton addStudentButton = new JButton("Add Student");

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve student information
                String studentName = studentNameField.getText();
                String studentId = studentIdField.getText();
                Boolean studentActiveStatus = studentStatusCheckBox.isSelected();

                // TODO: Add code to insert the student data into the StudentLog table
                // Clear the form fields after adding the student
                studentNameField.setText("");
                studentIdField.setText("");
                studentStatusCheckBox.setSelected(false);
            }
        });

        // Add components to addStudentPanel
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        addStudentPanel.add(new JLabel("Student Name:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        addStudentPanel.add(studentNameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        addStudentPanel.add(new JLabel("Student ID:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        addStudentPanel.add(studentIdField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        addStudentPanel.add(studentStatusCheckBox, gridBagConstraints); // Add the checkbox

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        addStudentPanel.add(addStudentButton, gridBagConstraints);

        // Tab for creating courses
        JPanel createCoursePanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Create Courses", null, createCoursePanel, "Create Courses");

        // Components for creating courses
        courseNameField = new JTextField(20);
        courseCodeField = new JTextField(20);
        courseCreditField = new JTextField(20);
        JButton createCourseButton = new JButton("Create Course");

        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve course information
                String courseName = courseNameField.getText();
                String courseCode = courseCodeField.getText();
                String courseCredit = courseCreditField.getText();

                // TODO: Add code to insert the course data into the CoursesLog table
                // Clear the form fields after creating the course
                courseNameField.setText("");
                courseCodeField.setText("");
                courseCreditField.setText("");
            }
        });

        // Add components to createCoursePanel
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        createCoursePanel.add(new JLabel("Course Title:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        createCoursePanel.add(courseNameField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        createCoursePanel.add(new JLabel("Course Code:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        createCoursePanel.add(courseCodeField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        createCoursePanel.add(new JLabel("Course Credit:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        createCoursePanel.add(courseCreditField, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        createCoursePanel.add(createCourseButton, gridBagConstraints);

        // Tab for assigning grades
        JPanel assignGradesPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Assign Grades", null, assignGradesPanel, "Assign Grades to Students");

        // Components for assigning grades
        assignStudentId = new JTextField(20);
        assignCourseId = new JTextField(20);
        gradeField = new JTextField(5);
        JButton assignGradeButton = new JButton("Assign Grade");

        assignGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve selected student, course, and grade
                String selectedStudent =  assignStudentId.getText();
                String selectedCourse = assignCourseId.getText();
                String grade = gradeField.getText();

                // TODO: Add code to update the corresponding course table with the assigned grade
                // Clear the form fields after assigning the grade
                gradeField.setText("");
            }
        });

        // Add components to assignGradesPanel
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        assignGradesPanel.add(new JLabel("Select Student:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        assignGradesPanel.add(assignStudentId, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        assignGradesPanel.add(new JLabel("Select Course:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        assignGradesPanel.add(assignCourseId, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        assignGradesPanel.add(new JLabel("Enter Grade:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        assignGradesPanel.add(gradeField, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        assignGradesPanel.add(assignGradeButton, gridBagConstraints);

        // Add components to assignGradesPanel
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        // Add tabbedPane to the main panel
        add(tabbedPane, BorderLayout.CENTER);

        // Logout button (unchanged)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(80, 25));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the login panel
                parentApp.switchPanel(new LoginPanel(parentApp));
            }
        });
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

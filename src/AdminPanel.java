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
        addComponentsToPanel(addStudentPanel, studentIdField, "Student ID:", 1, 0);
        addComponentsToPanel(addStudentPanel, studentStatusCheckBox, "Student Status:", 2, 0);
        addComponentsToPanel(addStudentPanel, addStudentButton, 1, 3);
    }

    private void setupCreateCoursesTab() {
        JPanel createCoursePanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Create Courses", null, createCoursePanel, "Create Courses");

        courseNameField = createTextField(20);
        courseCodeField = createTextField(20);
        courseCreditField = createTextField(20);
        JButton createCourseButton = createButton("Create Course", e -> createCourse());

        addComponentsToPanel(createCoursePanel, courseNameField, "Course Title:", 0, 0);
        addComponentsToPanel(createCoursePanel, courseCodeField, "Course Code:", 1, 0);
        addComponentsToPanel(createCoursePanel, courseCreditField, "Course Credit:", 2, 0);
        addComponentsToPanel(createCoursePanel, createCourseButton, 1, 3);
    }

    private void setupAssignGradesTab() {
        JPanel assignGradesPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Assign Grades", null, assignGradesPanel, "Assign Grades to Students");

        assignStudentIdField = createTextField(20);
        assignCourseIdField = createTextField(20);
        gradeField = createTextField(5);
        JButton assignGradeButton = createButton("Assign Grade", e -> assignGrade());

        addComponentsToPanel(assignGradesPanel, assignStudentIdField, "Select Student:", 0, 0);
        addComponentsToPanel(assignGradesPanel, assignCourseIdField, "Select Course:", 1, 0);
        addComponentsToPanel(assignGradesPanel, gradeField, "Enter Grade:", 2, 0);
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

    private void addComponentsToPanel(JPanel panel, JComponent component, String label, int gridx, int gridy) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.fill = (gridy == 3) ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE;

        if (!label.isEmpty()) {
            panel.add(new JLabel(label), gridBagConstraints);
            gridBagConstraints.gridx++;
        }

        panel.add(component, gridBagConstraints);
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

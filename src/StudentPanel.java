import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPanel extends JPanel {
    private App parentApp;
    private JTabbedPane tabbedPane;

    // Components for enrolling in courses
    private JTable availableCoursesTable;
    private JTextField courseCodeField;
    private JButton enrollButton;

    // Component for viewing enrolled courses and grades
    private JTable enrolledCoursesTable;

    public StudentPanel(App app) {
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
        enrolledCoursesTableModel.addColumn("Course ID");
        enrolledCoursesTableModel.addColumn("Course Name");
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

    private void populateAvailableCoursesTable() {
        // TODO: Implement this method to fetch and populate available courses into the availableCoursesTable
        // Random data for now:
        DefaultTableModel model = (DefaultTableModel) availableCoursesTable.getModel();
        model.addRow(new Object[] {"C001", "Course 1", "3"});
        model.addRow(new Object[] {"C002", "Course 2", "4"});
    }

    private void populateEnrolledCoursesTable() {
        // TODO: Implement this method to fetch and populate enrolled courses and grades into the enrolledCoursesTable
        // Random data for now:
        DefaultTableModel model = (DefaultTableModel) enrolledCoursesTable.getModel();
        model.addRow(new Object[] {"C001", "Course 1", "A"});
        model.addRow(new Object[] {"C002", "Course 2", "B"});
    }

    private void enrollInCourse() {
        // TODO: Implement enroll
    }
}

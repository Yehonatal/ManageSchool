# ManageSchool

This is a simple Student Management System project with a focus on managing students, courses, and grades.

## Program Structure

-   **Admin:**

    -   Add students
    -   Manage available courses
    -   Assign grades

-   **Students:**
    -   Enroll in courses
    -   Drop enrolled courses
    -   View grade reports

## Page Walkthrough

### Login Page

-   **Admin Login:**

    -   Access admin-specific functionalities
    -   Add or remove students to/from the database
    -   Create courses for student enrollment
    -   Generate grade reports based on enrolled courses

-   **Student Login:**
    -   Enroll in courses or drop currently enrolled courses
    -   View enrolled courses with associated grades

## Database Structure

### Admin Table

| Column   | Type        |
| -------- | ----------- |
| AdminId  | Primary key |
| Name     | VARCHAR(50) |
| Username | VARCHAR(50) |
| Password | VARCHAR(50) |

### Courses Table

| Column   | Type        |
| -------- | ----------- |
| CourseId | Primary key |
| Title    | VARCHAR(50) |
| Credit   | VARCHAR(50) |

### Student Table

| Column    | Type        |
| --------- | ----------- |
| StudentId | Primary key |
| Name      | VARCHAR(50) |
| Status    | VARCHAR(50) |

### Enrollments Table

| Column           | Type        |
| ---------------- | ----------- |
| EnrolledStudId   | Foreign key |
| EnrolledCourseId | Foreign key |
| Grade            | VARCHAR(50) |

## Possible Functionalities

-   **Authentication:**

    -   Verify user identity as admin or student during login.

-   **Admin Actions:**

    -   Add students, create courses, and assign grades.
    -   Insert records into StudentLog table for new students.
    -   Insert records into CoursesLog table for new courses.
    -   Update corresponding course tables to assign grades.

-   **Student Actions:**
    -   Enroll in courses and check grades.
    -   Insert records into Enrollments table for course enrollment.
    -   Query corresponding course tables to view grades.

---

In this project am planning to create a basic student management system with essential functionalities. As i develop it further, am gonna consider implementing additional features, enhancing the user interface, and following best practices for security and a some what better data management

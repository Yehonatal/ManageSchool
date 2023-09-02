CREATE DATABASE IF NOT EXISTS ManageSchool;

USE ManageSchool;

-- Admin table
CREATE TABLE AdminLog(adminId INT PRIMARY KEY, adminName VARCHAR(50), adminUserName VARCHAR(50), adminUserPsw VARCHAR(50));
INSERT INTO AdminLog VALUES (101, 'admin', 'admin', 'admin');

-- Student table
CREATE TABLE StudentLog(studentId INT PRIMARY KEY, studentName VARCHAR(50), studentStatus VARCHAR(50));
INSERT INTO StudentLog VALUES (101, 'stud', 'Active');

-- Courses table
CREATE TABLE CoursesLog(courseId INT PRIMARY KEY, courseTitle VARCHAR(50), courseCredit VARCHAR(50));
INSERT INTO CoursesLog VALUES (101, 'advancedProgramming', '3');

-- Insert data into the advancedProgramming course table
INSERT INTO advancedProgramming VALUES (101, 'A');

-- A junction table for associating students with courses both column which are studentId and CoursesId will be referances 
CREATE TABLE student_course (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade VARCHAR(2) DEFAULT 'A', -- Set the default value to 'A'
    FOREIGN KEY (student_id) REFERENCES studentlog(studentId),
    FOREIGN KEY (course_id) REFERENCES courseslog(courseId)
);

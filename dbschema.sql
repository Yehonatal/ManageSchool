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
CREATE TABLE Enrollments(enrolledStudId INT NOT NULL, enrolledCourseId INT NOT NULL, studentCourseGrade CHAR,
FOREIGN KEY(enrolledStudId) REFERENCES StudentLog(studentId),
FOREIGN KEY(enrolledCourseId) REFERENCES CoursesLog(courseId));

INSERT INTO Enrollments VALUES (101, 101, 'A');
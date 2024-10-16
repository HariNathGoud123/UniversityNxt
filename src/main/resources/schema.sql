create table if not exists professor(
     id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL
);
create table if not exists student(
     id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);


create table if not exists course(
     id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credits INT,
    professorId INT,
    FOREIGN KEY (professorId) REFERENCES professor(id)
);


CREATE TABLE IF NOT EXISTS course_student (
  studentId INT, 
  courseId INT, 
  PRIMARY KEY (studentId, courseId),  
  FOREIGN KEY (studentId) REFERENCES student(id), 
  FOREIGN KEY (courseId) REFERENCES course(id) 
);

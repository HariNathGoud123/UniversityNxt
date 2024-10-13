INSERT INTO professor (name, department) VALUES
  ('John Smith', 'Computer Science'),
  ('Mary Johnson', 'Physics'),
  ('David Lee', 'Mathematics');


  INSERT INTO student (name, email) VALUES
  ('Alice Johnson', 'alice@example.com'),
  ('Bob Davis', 'bob@example.com'),
  ('Eva Wilson', 'eva@example.com');

  INSERT INTO course (name, credits, professorId) VALUES
  ('Introduction to Programming', 3, 1),
  ('Quantum Mechanics', 4, 2),
  ('Calculus', 4, 3);
  
  INSERT INTO course_student (studentid, courseid) VALUES
  (1, 1),
  (1,2),
  (2, 2),
  (2, 3),
  (3, 1),
  (3, 3);
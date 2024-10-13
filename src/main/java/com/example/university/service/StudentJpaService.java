/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */
package com.example.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.*;

import com.example.university.model.Course;
import com.example.university.model.Professor;
import com.example.university.model.Student;
import com.example.university.repository.*;

@Service
public class StudentJpaService implements StudentRepository {
	@Autowired
	CourseJpaRepository courseJpaRepository;

	@Autowired
	StudentJpaRepository studentJpaRepository;

	@Override
	public ArrayList<Student> getStudents() {
		List<Student> students = studentJpaRepository.findAll();
		return new ArrayList<>(students);
	}

	@Override
	public Student getStudentById(int studentId) {

		try {
			Student student = studentJpaRepository.findById(studentId).get();
			return student;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student addStudent(Student student) {

		try {
			List<Integer> courseIds = new ArrayList<>();
			for (Course course : student.getCourses()) {
				courseIds.add(course.getCourseId());
			}
			List<Course> courses = courseJpaRepository.findAllById(courseIds);
			if (courseIds.size() != courses.size()) {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of courses are not found");
			}
			student.setcourses(courses);
			for (Course course : courses) {
				course.getStudents().add(student);
			}
			Student saveStudent = studentJpaRepository.save(student);
			courseJpaRepository.saveAll(courses);

			return saveStudent;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Student upadateStudent(Student student, int studentId) {
		try {
			Student newStudent = studentJpaRepository.findById(studentId).get();
			if (student.getStudentName() != null) {
				newStudent.setStudentName(student.getStudentName());
			}
			if (student.getEmail() != null) {
				newStudent.setEmail(student.getEmail());
			}
			if (student.getCourses() != null) {
				ArrayList<Integer> courseIds = new ArrayList<>();
				List<Course> courses = student.getCourses();
				for (Course course : courses) {
					course.getStudents().remove(newStudent);
				}
				courseJpaRepository.saveAll(courses);
				List<Course> correct_courses = courseJpaRepository.findAllById(courseIds);
				for (Course course : correct_courses) {
					course.getStudents().add(newStudent);
				}
				courseJpaRepository.saveAll(correct_courses);
				newStudent.setcourses(correct_courses);
			}
			return studentJpaRepository.save(newStudent);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteStudentById(int studentId) {
		try {
			Student student = studentJpaRepository.findById(studentId).get();
			List<Course> courses = student.getCourses();
			for (Course course : courses) {
				course.getStudents().remove(student);
			}
			courseJpaRepository.saveAll(courses);
			studentJpaRepository.deleteById(studentId);
		}

		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	@Override
	public List<Course> getStudentCourses(int studentId) {
		try {
			Student student = studentJpaRepository.findById(studentId).get();
			List<Course> courses = student.getCourses();
			return courses;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}
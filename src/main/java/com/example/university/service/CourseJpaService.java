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
import com.example.university.model.*;
import com.example.university.repository.*;

@Service
public class CourseJpaService implements CourseRepository {
	@Autowired
	CourseJpaRepository courseJpaRepository;

	@Autowired
	StudentJpaRepository studentJpaRepository;

	@Autowired
	ProfessorJpaRepository professorJpaRepository;

	@Override
	public ArrayList<Course> getCourses() {
		List<Course> courses = courseJpaRepository.findAll();
		return new ArrayList<>(courses);
	}

	@Override
	public Course getCourseById(int courseId) {
		try {
			Course course = courseJpaRepository.findById(courseId).get();

			return course;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Course addCourse(Course course) {

		try {
			int professorId = course.getProfessor().getProfessorId();
			Professor professor = professorJpaRepository.findById(professorId).get();
			course.setProfessor(professor);
			ArrayList<Integer> StudentIds = new ArrayList<>();
			for (Student student : course.getStudents()) {
				StudentIds.add(student.getStudentId());
			}
			List<Student> complete_students = studentJpaRepository.findAllById(StudentIds);
			if (complete_students.size() != StudentIds.size()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of Students are not found");
			}
			course.setStudents(complete_students);
			courseJpaRepository.save(course);
			return course;
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
		}

	}

	@Override
	public Course updateCourse(Course course, int courseId) {
		try {
			// Retrieve the existing book entity
			Course newcourse = courseJpaRepository.findById(courseId).get();

			// Update all other fields of the book entity
			if (course.getCourseName() != null) {
				newcourse.setCourseName(course.getCourseName());
			}
			if (course.getCredits() != 0) {
				newcourse.setCredits(course.getCredits());
			}
			if (course.getProfessor() != null) {
				Professor professor = course.getProfessor();
				int professorId = professor.getProfessorId();
				Professor newProfessor = professorJpaRepository.findById(professorId).get();
				newcourse.setProfessor(newProfessor);
			}

			// Update authors along with the association
			if (course.getStudents() != null) {
				// Extract author IDs from the request object
				ArrayList<Integer> StudentIds = new ArrayList<>();
				for (Student student : course.getStudents()) {
					StudentIds.add(student.getStudentId());
				}
				List<Student> complete_students = studentJpaRepository.findAllById(StudentIds);
				if (complete_students.size() != StudentIds.size()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of Students are not found");
				}
				newcourse.setStudents(complete_students);
				courseJpaRepository.save(course);
			}
			// Save and return the book entity
			return courseJpaRepository.save(newcourse);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
		}
	}

	@Override
	public void deleteCourseById(int courseId) {
		try {
			courseJpaRepository.deleteById(courseId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);

	}

	@Override
	public Professor getCourseProfessor(int courseId) {
		try {
			Course course = courseJpaRepository.findById(courseId).get();
			Professor professor = course.getProfessor();
			return professor;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Student> getCourseStudentsById(int courseId) {
		try {
			Course course = courseJpaRepository.findById(courseId).get();
			return course.getStudents();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
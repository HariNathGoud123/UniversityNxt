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
import com.example.university.repository.*;

@Service
public class ProfessorJpaService implements ProfessorRepository {
	@Autowired
	ProfessorJpaRepository professorJpaRepository;
	@Autowired
	CourseJpaRepository courseJpaRepository;

	@Override
	public ArrayList<Professor> getProfessors() {
		List<Professor> professors = professorJpaRepository.findAll();
		return new ArrayList<>(professors);
	}

	@Override
	public Professor getProfessorById(int profressorId) {
		try {
			Professor professor = professorJpaRepository.findById(profressorId).get();
			return professor;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Professor addProfessor(Professor professor) {
		professorJpaRepository.save(professor);
		return professor;
	}

	@Override
	public Professor updateProfessor(Professor professor, int professorId) {
		try {
			Professor newProfessor = professorJpaRepository.findById(professorId).get();
			if (professor.getProfessorName() != null) {
				newProfessor.setProfessorName(professor.getProfessorName());
			}
			if (professor.getDepartment() != null) {
				newProfessor.setDepartMent(professor.getDepartment());
			}
			professorJpaRepository.save(newProfessor);
			return newProfessor;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteProfessorById(int professorId) {
		try {
			professorJpaRepository.deleteById(professorId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);

	}

	@Override
	public List<Course> getProfessorCourses(int professorId) {
		try {
			Professor professor = professorJpaRepository.findById(professorId).get();
			return courseJpaRepository.findByProfessor(professor);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
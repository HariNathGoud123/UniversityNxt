/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

package com.example.university.repository;

import java.util.*;
import com.example.university.model.*;

public interface ProfessorRepository {
    ArrayList<Professor> getProfessors();

    Professor getProfessorById(int profressorId);

    Professor addProfessor(Professor professor);

    Professor updateProfessor(Professor professor, int professorId);

    void deleteProfessorById(int professorId);

    List<Course> getProfessorCourses(int professorId);

}
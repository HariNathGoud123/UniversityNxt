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

public interface StudentRepository {

    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    Student addStudent(Student student);

    Student upadateStudent(Student student, int studentId);

    void deleteStudentById(int studentId);

    List<Course> getStudentCourses(int stuentId);

}
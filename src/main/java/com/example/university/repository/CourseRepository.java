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

public interface CourseRepository {
    ArrayList<Course> getCourses();

    Course getCourseById(int courseId);

    Course addCourse(Course course);

    Course updateCourse(Course course, int courseId);

    void deleteCourseById(int courseId);

    Professor getCourseProfessor(int courseId);

    List<Student> getCourseStudentsById(int courseId);
}
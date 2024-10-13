/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

package com.example.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.university.service.*;
import com.example.university.model.*;

@RestController
public class CourseController {
    @Autowired
    CourseJpaService courseJpaService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseJpaService.getCourses();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourseById(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseById(courseId);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return courseJpaService.addCourse(course);
    }

    @PutMapping("courses/{courseId}")
    public Course updatecourse(@RequestBody Course course, @PathVariable("courseId") int courseId) {
        return courseJpaService.updateCourse(course, courseId);
    }

    @DeleteMapping("courses/{courseId}")
    public void deleteCourseById(@PathVariable("courseId") int courseId) {
        courseJpaService.deleteCourseById(courseId);
    }

    @GetMapping("/courses/{courseId}/professor")
    public Professor getCourserProfessor(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseProfessor(courseId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> ListCourseStudents(@PathVariable("courseId") int courseId) {
        return courseJpaService.getCourseStudentsById(courseId);
    }

}
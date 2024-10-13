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
public class StudentController {
    @Autowired
    StudentJpaService studentJpaService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentJpaService.getStudents();
    }

    @GetMapping("students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentJpaService.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentJpaService.addStudent(student);
    }

    @PutMapping("students/{studentId}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("studentId") int studentId) {
        return studentJpaService.upadateStudent(student, studentId);
    }

    @DeleteMapping("students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentJpaService.deleteStudentById(studentId);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getStudentcourses(@PathVariable("studentId") int studentId) {
        return studentJpaService.getStudentCourses(studentId);
    }

}

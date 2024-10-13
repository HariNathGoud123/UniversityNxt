/*
 *
 * You can use the following import statements
 * 
 * import javax.persistence.*;
 * 
 */
package com.example.university.model;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private int courseId;
    @Column(name = "name")
    private String courseName;
    @Column(name = "credits")
    private int credits;
    @ManyToOne
    @JoinColumn(name = "professorid")
    private Professor professor;
    @ManyToMany
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "studentid"), inverseJoinColumns = @JoinColumn(name = "courseid"))
    @JsonIgnoreProperties("courses")
    private List<Student> students = new ArrayList<>();

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Course() {
    }

    public Course(int courseId, String courseName, int credits, Professor professor, List<Student> students) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.professor = professor;
        this.students = students;
    }

}
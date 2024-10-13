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
@Table(name = "student")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int studentId;
  @Column(name = "name")

  private String studentName;
  @Column(name = "email")
  private String email;
  @ManyToMany(mappedBy = "students")
  @JsonIgnoreProperties("students")
  private List<Course> courses;

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public int getStudentId() {
    return this.studentId;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentName() {
    return this.studentName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setcourses(List<Course> courses) {
    this.courses = courses;
  }

  public List<Course> getCourses() {
    return this.courses;
  }

  public Student() {
  }

  public Student(int studentId, String studentName, String email, List<Course> courses) {
    this.studentId = studentId;
    this.studentName = studentName;
    this.email = email;
    this.courses = courses;
  }
}
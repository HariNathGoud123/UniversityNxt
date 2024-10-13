/*
 *
 * You can use the following import statements
 * 
 * import javax.persistence.*;
 * 
 */

package com.example.university.model;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int professorId;
    @Column(name = "name")
    private String professorName;
    @Column(name = "department")
    private String department;

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public void setDepartMent(String department) {
        this.department = department;
    }

    public String getDepartment() {

        return this.department;
    }

    public Professor() {
    }

    public Professor(int professorId, String professorName, String department) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.department = department;
    }
}

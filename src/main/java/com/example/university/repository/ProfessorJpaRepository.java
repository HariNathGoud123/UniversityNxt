/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.data.jpa.repository.JpaRepository;
 * import org.springframework.stereotype.Repository;
 * 
 */

package com.example.university.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.university.model.Professor;

@Repository
public interface ProfessorJpaRepository extends JpaRepository<Professor, Integer> {

}
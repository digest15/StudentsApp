package com.example.application.backend.repository;

import com.example.application.backend.entity.Student;
import com.example.application.backend.dto.student.StudentDtoForGrid;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    @EntityGraph(attributePaths = {"group"})
    List<Student> findAll();

    @Query("from Student s join fetch s.group g where g.number like CONCAT(:groupNumber, '%')")
    List<Student> findByGroupNumberStartingWith(String groupNumber);

    @Query("from Student s where s.fullName like CONCAT(:fullName, '%') and s.group.number like CONCAT(:groupNumber, '%')")
    List<Student> findByFullNameAndGroupNumber(String fullName, String groupNumber);
}

package com.example.application.backend.repository;

import com.example.application.backend.entity.Student;
import com.example.application.backend.dto.student.StudentDtoForGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContaining(String name);
}

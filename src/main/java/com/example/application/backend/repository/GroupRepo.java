package com.example.application.backend.repository;

import com.example.application.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    List<Group> findBySpecialtyNameContaining(String specialtyName);
}

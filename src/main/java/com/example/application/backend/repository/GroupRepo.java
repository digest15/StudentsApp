package com.example.application.backend.repository;

import com.example.application.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query("from Group e " +
            "where concat(e.specialtyName, ' ', e.number) " +
                "like concat('%', :pattern, '%')")
    List<Group> findByNameOrNumber(@Param("pattern") String pattern);
}

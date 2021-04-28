package com.example.application.backend.repository;

import com.example.application.backend.entity.Group;
import com.example.application.backend.dto.GroupDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query("select e.id as id, e.specialtyName as specialtyName, e.number as number from Group e " +
                "where concat(specialtyName, ' ', number)" +
                    "like concat('%', :pattern, '%')")
    List<GroupDTO> getAllForDto(@Param("pattern") String pattern);

    @Query("select e.id as id, e.specialtyName as specialtyName, e.number as number from Group e")
    List<GroupDTO> getAllForDto();

    @Query("from Group e " +
            "where concat(e.specialtyName, ' ', e.number) " +
                "like concat('%', :pattern, '%')")
    List<Group> findAllForPattern(@Param("pattern") String pattern);
}

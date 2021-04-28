package com.example.application.backend.repository;

import com.example.application.backend.entity.Student;
import com.example.application.backend.projections.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    //TODO Транзакции???
    @Query("select e.id as id, e.lastName as lastName, e.firstName as firstName, e.patronomic as patronomic, e.birthday as birthday, " +
            "e.group.number as groupNumber,  e.group.specialtyName as specialtyName from Student e " +
            "where concat(lastName, ' ', firstName, ' ', patronomic, ' ', e.group.number) " +
            "like concat('%', :pattern, '%')")
    List<StudentDTO> getAllForDto(@Param("pattern") String pattern);

    @Query("select e.id as id, e.lastName as lastName, e.firstName as firstName, e.patronomic as patronomic, e.birthday as birthday, " +
            "e.group.number as groupNumber,  e.group.specialtyName as specialtyName from Student e")
    List<StudentDTO> getAllForDto();

    @Query("from Student e " +
            "where concat(lastName, ' ', firstName, ' ', patronomic, ' ', groupNumber)" +
            "like concat('%', :pattern, '%')")
    List<Student> findAllForPattern(@Param("pattern") String pattern);
}

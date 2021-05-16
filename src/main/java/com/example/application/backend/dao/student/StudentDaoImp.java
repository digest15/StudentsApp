package com.example.application.backend.dao.student;

import com.example.application.backend.dao.group.GroupDaoImp;
import com.example.application.backend.dto.student.StudentDto;
import com.example.application.backend.dto.student.StudentDtoForGrid;
import com.example.application.backend.entity.Student;
import com.example.application.backend.repository.StudentRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImp implements StudentDao {
    private StudentRepo repository;
    private StudentMapper mapper;

    @Autowired
    public StudentDaoImp(StudentRepo repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto findById(Long id) {
        return mapper.fromStudent(repository.findById(id).orElse(null));
    }

    @Override
    public List<StudentDto> getItems() {
        return mapper.fromStudents(repository.findAll());
    }

    @Override
    public List<StudentDto> getItems(String pattern) {
        if (pattern.isEmpty()) {
            return getItems();
        }else {
            return mapper.fromStudents(repository.findByGroupNumberStartingWith(pattern));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDtoForGrid> getItemsForGrid() {
        return mapper.fromStudentsToForGrid(repository.findAll());
    }


    @Override
    @Transactional(readOnly = true)
    public List<StudentDtoForGrid> getItemsForGrid(String pattern) {
        return mapper.fromStudentsToForGrid(repository.findByGroupNumberStartingWith(pattern));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDtoForGrid> getItemsForGrid(String patternByFullName, String patternByGroupNumber) {
        return mapper.fromStudentsToForGrid(repository.findByFullNameAndGroupNumber(patternByFullName, patternByGroupNumber));
    }

    @Override
    public void save(StudentDto item) {
        repository.save(mapper.toStudent(item));
    }

    @Override
    public void delete(StudentDto item) {
        repository.delete(mapper.toStudent(item));
    }

    @Mapper(uses = {GroupDaoImp.GroupMapper.class})
    public interface StudentMapper {

        List<StudentDtoForGrid> fromStudentsToForGrid(List<Student> sourceCollection);

        @Mapping(source = "group.specialtyName", target = "specialtyName")
        @Mapping(source = "group.number", target = "groupNumber")
        StudentDtoForGrid fromStudentsToForGrid(Student student);


        List<StudentDto> fromStudents(List<Student> sourceCollection);

        StudentDto fromStudent(Student student);


        List<Student> toStudents(List<StudentDto> dtoCollection);

        Student toStudent(StudentDto dto);
    }
}

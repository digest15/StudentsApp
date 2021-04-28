package com.example.application.backend.services.student;

import com.example.application.backend.entity.Student;
import com.example.application.backend.projections.StudentDTO;
import com.example.application.backend.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImp implements StudentService {
    private StudentRepo repository;

    @Autowired
    public StudentServiceImp(StudentRepo repository) {
        this.repository = repository;
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getItems() {
        return repository.findAll();
    }

    @Override
    public List<Student> getItems(String pattern) {
        if (pattern.isEmpty()) {
            return getItems();
        }else {
            return repository.findAllForPattern(pattern);
        }
    }

    @Override
    public List<StudentDTO> getItemsForGrid() {
        return repository.getAllForDto();
    }

    @Override
    public List<StudentDTO> getItemsForGrid(String pattern) {
        if (pattern.isEmpty()) {
            return getItemsForGrid();
        }else {
            return repository.getAllForDto(pattern);
        }
    }

    @Override
    public void save(Student item) {
        repository.save(item);
    }

    @Override
    public void delete(Student item) {
        repository.delete(item);
    }
}

package com.example.application.backend.services.student;

import com.example.application.backend.entity.Student;
import com.example.application.backend.dto.StudentDTO;
import com.example.application.backend.services.base.IdEntityService;
import com.example.application.backend.services.base.StandartEntityService;

public interface StudentService extends StandartEntityService<Student, StudentDTO>, IdEntityService<Student, Long> {
}

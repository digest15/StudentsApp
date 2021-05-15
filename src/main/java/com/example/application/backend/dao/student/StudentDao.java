package com.example.application.backend.dao.student;

import com.example.application.backend.dto.student.StudentDto;
import com.example.application.backend.entity.Student;
import com.example.application.backend.dto.student.StudentDtoForGrid;
import com.example.application.backend.dao.base.IdEntityDao;
import com.example.application.backend.dao.base.StandartEntityDao;

public interface StudentDao extends StandartEntityDao<StudentDto, StudentDtoForGrid>, IdEntityDao<StudentDto, Long> {
}

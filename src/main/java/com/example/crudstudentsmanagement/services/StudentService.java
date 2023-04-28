package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.StudentModel;

import java.util.List;

public interface StudentService {
    List<StudentModel> getAllStudents();
    StudentModel getStudentById(Long id);
    void saveStudent(StudentModel student);
    void deleteStudent(Long id);
}


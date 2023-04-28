package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.StudentModel;
import com.example.crudstudentsmanagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<StudentModel> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentModel getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(StudentModel student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

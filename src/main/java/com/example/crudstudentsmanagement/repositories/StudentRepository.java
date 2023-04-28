package com.example.crudstudentsmanagement.repositories;

import com.example.crudstudentsmanagement.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
}


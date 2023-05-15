package com.example.crudstudentsmanagement.controllers;

import com.example.crudstudentsmanagement.models.StudentModel;
import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.services.StudentService;
import com.example.crudstudentsmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<StudentModel> students = this.studentService.getAllStudents();
        model.addAttribute("students", students);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel userModel = userService.getUserByUsername(username);
        model.addAttribute("user", userModel);

        return "students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        StudentModel student = this.studentService.getStudentById(id);
        model.addAttribute("student", student);

        return "edit-student-form";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new StudentModel());

        return "create-student-form";
    }

    @PostMapping("/edit")
    public String updateStudent(@ModelAttribute("student") StudentModel student) {
        StudentModel updateStudent = this.studentService.getStudentById(student.getId());
        updateStudent.setName(student.getName());
        updateStudent.setLastName(student.getLastName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setPhone(student.getPhone());
        this.studentService.saveStudent(updateStudent);

        return "redirect:/students";
    }

    @PostMapping("/create")
    public String saveStudent(@ModelAttribute("student") StudentModel student) {
        this.studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);

        return "redirect:/students";
    }
}

package com.example.crudstudentsmanagement.controllers;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.services.AuthorityService;
import com.example.crudstudentsmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    AuthorityService authorityService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        List<AuthorityModel> authorities = authorityService.getAllPossibleAuthorities();
        model.addAttribute("authorityTypes", authorities);  // tuve que llamarlo asi porque se pisaba con authorities de spring security
        model.addAttribute("user", new UserModel());

        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(@ModelAttribute("user") UserModel user){
        this.userService.saveUser(user);    // casos borde: username ya existe, pw vacia

        return "redirect:/login";
    }
}

package com.example.crudstudentsmanagement.controllers;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.services.AuthorityService;
import com.example.crudstudentsmanagement.services.UserService;
import com.example.crudstudentsmanagement.utils.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("authorityTypes", authorities);  // AttributeName must be different to "authorities" because it overlaps spring authorities
        model.addAttribute("user", new UserModel());

        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(@Valid @ModelAttribute("user") UserModel user, BindingResult result, Model model){
        List<AuthorityModel> authorities = authorityService.getAllPossibleAuthorities();
        model.addAttribute("authorityTypes", authorities);

        if (result.hasErrors()) {
            return "sign-up";
        }
        try {
            this.userService.saveUser(user);
        } catch (UserAlreadyExistsException e){
            result.rejectValue("username", "error.username", e.getMessage());
            return "sign-up";
        }

        return "redirect:/login";
    }
}

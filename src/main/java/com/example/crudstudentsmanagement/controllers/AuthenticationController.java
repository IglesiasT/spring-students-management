package com.example.crudstudentsmanagement.controllers;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.models.UserModel;
import com.example.crudstudentsmanagement.services.AuthorityService;
import com.example.crudstudentsmanagement.services.UserService;
import com.example.crudstudentsmanagement.utils.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String signUpUser(@ModelAttribute("user") UserModel user, RedirectAttributes redirectAttributes){
        try {
            this.userService.saveUser(user);
        } catch (UserAlreadyExistsException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/sign-up";
        }

        // Show successful sign up
        redirectAttributes.addFlashAttribute("success", "Thank you for signing up");
        return "redirect:/login";
    }
}

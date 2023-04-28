package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.repositories.AuthorityRepository;
import com.example.crudstudentsmanagement.utils.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public AuthorityModel getAuthorityByName(AuthorityName name) {
        return this.authorityRepository.findByName(name);
    }

    @Override
    public List<AuthorityModel> getAllPossibleAuthorities() {
        return this.authorityRepository.findAll();
    }
}

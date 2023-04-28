package com.example.crudstudentsmanagement.services;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.utils.AuthorityName;

import java.util.List;

public interface AuthorityService {
    AuthorityModel getAuthorityByName(AuthorityName name);
    List<AuthorityModel> getAllPossibleAuthorities();
}

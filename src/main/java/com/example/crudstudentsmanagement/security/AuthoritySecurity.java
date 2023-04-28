package com.example.crudstudentsmanagement.security;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AuthoritySecurity implements GrantedAuthority {
    private final AuthorityModel authority;

    @Override
    public String getAuthority(){
        return this.authority.getName().toString();
    }
}

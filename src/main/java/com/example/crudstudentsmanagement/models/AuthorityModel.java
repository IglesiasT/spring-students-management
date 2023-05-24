package com.example.crudstudentsmanagement.models;

import com.example.crudstudentsmanagement.utils.AuthorityName;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authorities")
@Setter
@NoArgsConstructor
public class AuthorityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private AuthorityName name;

    public AuthorityModel(AuthorityName name){
        this.name = name;
    }

    public static AuthorityModel Admin(){
        return new AuthorityModel(AuthorityName.ADMIN);
    }

    public static AuthorityModel Student() {
        return new AuthorityModel(AuthorityName.STUDENT);
    }

    public Long getId() {
        return id;
    }

    public AuthorityName getName() {
        return name;
    }
}

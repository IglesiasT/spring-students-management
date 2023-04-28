package com.example.crudstudentsmanagement.repositories;

import com.example.crudstudentsmanagement.models.AuthorityModel;
import com.example.crudstudentsmanagement.utils.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityModel, Long> {
    AuthorityModel findByName(AuthorityName name);
}

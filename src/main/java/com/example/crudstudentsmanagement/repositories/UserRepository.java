package com.example.crudstudentsmanagement.repositories;

import com.example.crudstudentsmanagement.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    /*
     al heredar de jparepository, Spring Data JPA sabe que debe implementar el metodo
     porque sigue la convención de nombrar los métodos de consulta como "findBy" seguido
     del nombre de la propiedad que se utilizará para realizar la búsqueda.
     */
    UserModel findByUsername(String username);
}

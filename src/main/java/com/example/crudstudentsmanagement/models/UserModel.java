package com.example.crudstudentsmanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;    // hacer que sea unico (constraint unique)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)    // LAZY = fetch when needed, EAGER = fetch immediately
    @JoinTable(name = "users_authorities",  // nombre de la tabla generada por el join
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),    // name columna nueva tabla, referenced con el nombre de esta columna que quiero joinear
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    private List<AuthorityModel> authorities;   // que un usuario tenga varias me permite usar many to many lo que esta bueno pero no tiene mucho sentido ser admin y user al mismo tiempo

    public UserModel(String username, String password, List<AuthorityModel> authorities){
        this.username= username;
        this.password= password;
        this.authorities= authorities;
    }
}

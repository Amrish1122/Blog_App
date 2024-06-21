package com.App_Boot.Application.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty
    @Size(min = 4,message = "user name must be greater than 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    @Column(name = "user_name")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be min of 3 char and max of 10 char")
    private String password;

    @NotEmpty
    private String about;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}

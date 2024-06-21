package com.App_Boot.Application.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

@NotBlank
@Size(min = 10,message = "minimum size of category title is 10 words")
    private String title;

@NotBlank
@Size(min = 10,message = "minimum size of category description is 10 words")
    private String description;

@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
private List<Post> posts = new ArrayList<>();

}

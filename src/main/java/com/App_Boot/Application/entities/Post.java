package com.App_Boot.Application.entities;

import com.App_Boot.Application.payload.CategoryDto;
import com.App_Boot.Application.payload.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title",length = 1000,nullable = false)
    private String title;

    @Column(name = "post_content",length = 400)
    private String content;

    @Column(name = "post_image")
    private String imageName;

    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}

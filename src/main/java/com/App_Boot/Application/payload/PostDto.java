package com.App_Boot.Application.payload;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

public class PostDto {
    private String postId;
    private String title;
    private String content;
    private  String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;

}

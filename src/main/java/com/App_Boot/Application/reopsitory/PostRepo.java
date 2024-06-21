package com.App_Boot.Application.reopsitory;

import com.App_Boot.Application.entities.Category;
import com.App_Boot.Application.entities.Post;
import com.App_Boot.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String keywords);
}

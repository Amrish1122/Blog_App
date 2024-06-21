package com.App_Boot.Application.reopsitory;

import com.App_Boot.Application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}

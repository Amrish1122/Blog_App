package com.App_Boot.Application.reopsitory;

import com.App_Boot.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}

package com.App_Boot.Application.controllers;

import com.App_Boot.Application.payload.ApiResponse;
import com.App_Boot.Application.payload.UserDto;
import com.App_Boot.Application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    //post-create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDtoCreated=this.userService.createUser(userDto);
        return new ResponseEntity<>(userDtoCreated, HttpStatus.CREATED);

    }

    //put_update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updateUser = this.userService.updateUser(userDto,userId);
        return  ResponseEntity.ok(updateUser);

    }

    //Delete_deleteUser
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);

    }
    //Get- AllUser
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    //Get- userGet
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId){
        UserDto userDtoGetUser=userService.getUserById(userId);
        if (userDtoGetUser==null){
            return new ResponseEntity<>("user does not exist",HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(userDtoGetUser,HttpStatus.OK);
    }

}

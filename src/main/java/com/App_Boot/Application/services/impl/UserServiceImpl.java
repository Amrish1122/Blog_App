package com.App_Boot.Application.services.impl;
import com.App_Boot.Application.entities.User;
import com.App_Boot.Application.exception.ResourceNotFoundException;
import com.App_Boot.Application.payload.UserDto;
import com.App_Boot.Application.reopsitory.UserRepo;
import com.App_Boot.Application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service


public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user =this.dtoToUser(userDto);
        User savesUser=this.userRepository.save(user);
        return this.userToDto(savesUser);
        //return this.modelMapper.map(savesUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("user","id",userId));
              user.setName(userDto.getName());
              user.setEmail(userDto.getEmail());
              user.setPassword(userDto.getPassword());
              user.setAbout(userDto.getAbout());
              User updatedUser=this.userRepository.save(user);
              UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user","id",userId));
       return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users =this.userRepository.findAll();
        List<UserDto>userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFoundException("user","id",userId));
        this.userRepository.delete(user);

    }
    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
     //   user.setId(userDto.getId());
       // user.setName(userDto.getName());
        //user.setAbout(userDto.getAbout());
        //user.setEmail(userDto.getEmail());
        //user.setPassword(userDto.getPassword());
        return user;


    }
    public UserDto userToDto(User user){
        UserDto userDto= this.modelMapper.map(user,UserDto.class);
       // userDto.setId(user.getId());
        //userDto.setName(user.getName());
        //userDto.setEmail(user.getEmail());
        //userDto.setPassword(user.getPassword());
        //userDto.setAbout(user.getAbout());
        return userDto;
    }
}

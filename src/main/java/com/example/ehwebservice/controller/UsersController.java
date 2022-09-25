package com.example.ehwebservice.controller;

import com.example.ehwebservice.dto.Posts;

import com.example.ehwebservice.dto.UsersRecord;
import com.example.ehwebservice.entities.Users;
import com.example.ehwebservice.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> findAll(){
        return usersService.findUsers();
    }

       @GetMapping("/{id}")
        public Users findUserById(@PathVariable int id){
            return usersService.findUserById(id);
        }
    @GetMapping("/{userId}/posts")
    public List<Posts> findUserByUserId(@PathVariable int userId){
        return usersService.findUserByUserId(userId);
    }

    @PostMapping
    public Users insertUsers(@RequestBody Users users){
        return usersService.insertUsers(users);
    }

    @PatchMapping("/{id}")
    public Users updateUsers(
            @PathVariable int id,
            @RequestBody UsersRecord usersRecord
            ){
        return usersService.updateUsers(id, usersRecord);
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable int id){
        usersService.delete(id);
        }
    }



package com.example.ehwebservice.controller;

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
    public List<Users> findUsers(){
        return usersService.findUsers();
    }

    @GetMapping("/{id}")
    public Users findUserById(@PathVariable int id){
        return usersService.findUserById(id);
    }

    @PostMapping
    public Users insertUsers(@RequestBody Users users){
        return usersService.insertUsers(users);
    }

}

package com.example.ehwebservice.service;

import com.example.ehwebservice.dto.Posts;
//import com.example.ehwebservice.dto.User;
import com.example.ehwebservice.dto.UsersRecord;
import com.example.ehwebservice.entities.Users;
import com.example.ehwebservice.repo.UsersRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepo usersRepo;
    private final WebClient webClient;

    public UsersService(UsersRepo usersRepo, WebClient webClient) {
        this.usersRepo = usersRepo;
        this.webClient = webClient;
    }

    public Users findUserById(int id) {
        return usersRepo.findUserById(id);
    }

    public List<Posts> findUserByUserId(int userId){
        return webClient
                .get()
                .uri("/users/"+userId+"/posts")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Posts.class))
                .buffer()
                .blockLast();
    }

    public List<Users> findUsers(){
        return usersRepo.findAll();
    }

    public Users insertUsers(Users users){
        return usersRepo.save(users);
    }

    public Users deleteUsers(Users users) {
        usersRepo.delete(usersRepo.getById(users.getId()));
        return deleteUsers(users);
    }

    public Users updateUsers(int id, UsersRecord usersRecord) {
        Users existingUser = usersRepo.findById(id).orElseThrow();

        if (usersRecord.username() !=null){
            existingUser.setUsername(usersRecord.username());
        }
        Users createdUsers= usersRepo.save(existingUser);
        return new Users(createdUsers.getUsername());
    }

    public void delete(int id) {
        usersRepo.deleteById(id);

    }
}

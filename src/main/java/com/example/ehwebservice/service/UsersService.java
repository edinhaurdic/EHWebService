package com.example.ehwebservice.service;

import com.example.ehwebservice.dto.Posts;
import com.example.ehwebservice.entities.Users;
import com.example.ehwebservice.repo.UsersRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        return webClient
                .get()
                .uri("/users/" + id)
                .exchangeToMono(user -> user.bodyToMono(Users.class))
                .block();
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


}

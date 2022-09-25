package com.example.ehwebservice.repo;

//import com.example.ehwebservice.dto.User;
import com.example.ehwebservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findUserById(int id);
}

package com.humber.Week8Security.repositories;

import com.humber.Week8Security.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    //get optional user by username
    public Optional<MyUser> findByUsername(String username);

}

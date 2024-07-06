package com.humber.Week8Security.services;

import com.humber.Week8Security.models.MyUser;
import com.humber.Week8Security.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //method to save the user information to the database(register)
    //0 = user already exists, 1 = successfully created
    public int saveUser(MyUser user){
        //check if the username already exists in the db
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return 0;
        }

        //encode the password with bcrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save the user information to the database
        userRepository.save(user);
        return 1;
    }

}

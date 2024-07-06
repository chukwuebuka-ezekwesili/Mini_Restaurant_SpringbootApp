package com.humber.Week8Security.services;

import com.humber.Week8Security.models.MyUser;
import com.humber.Week8Security.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //loadUserByUsername method fetches user details from the data source(user repo)
    // and transforms them into a userDetails object that spring security can work with
    // for aurhentication and authorization
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> userOp = userRepository.findByUsername(username);

        if(userOp.isPresent()){
            MyUser user = userOp.get();

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }
}

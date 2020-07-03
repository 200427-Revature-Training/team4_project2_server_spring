package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.revature.entities.JwtUser;
import com.revature.repositories.JwtRepository;



@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtRepository jwtRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        JwtUser user = jwtRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(),
                new ArrayList<>());
    }

}

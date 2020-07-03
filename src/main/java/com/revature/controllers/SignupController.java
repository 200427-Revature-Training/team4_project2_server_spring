package com.revature.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.JwtUser;
import com.revature.repositories.JwtRepository;
import com.revature.utils.ValidationException;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "*")
public class SignupController {

	@Autowired
	JwtRepository jwtRepository;
	
	@PostMapping
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (jwtRepository.existsByUsername(username)){

            throw new ValidationException("Username already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String email = body.get("email");
        jwtRepository.save(new JwtUser(username, encodedPassword, firstName, lastName, email));
        return true;
    }


}

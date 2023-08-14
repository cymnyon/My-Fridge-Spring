package com.myfridge.service.impl;

import com.myfridge.model.User;
import com.myfridge.repository.UserRepository;
import com.myfridge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//package com.myfridge.service;


@Service
public class UserServiceImpl extends UserService {
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUserLoggedIn() {
        // Use Spring Security's authentication context to check if a user is logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser");
    }

    @Override
    public User getCurrentUser() {
        // Use Spring Security's authentication context to get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Override
    public User createUser(String username, String password, String nickname) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // add hashing function to the password before saving using BCrypt
        newUser.setNickname(nickname);

        return userRepository.save(newUser);
    }
}
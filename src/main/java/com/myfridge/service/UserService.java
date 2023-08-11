package com.myfridge.service;

import com.myfridge.model.User;
import com.myfridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfridge.model.User;

@Service
public abstract class UserService {

    private UserRepository userRepository = null;
    
    @Autowired
    public UserService() {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public abstract boolean isUserLoggedIn();

    public abstract User getCurrentUser();

    public abstract User findByUsername(String username);

    public abstract User createUser(User user);

    // Add more methods as needed for user-related operations

}

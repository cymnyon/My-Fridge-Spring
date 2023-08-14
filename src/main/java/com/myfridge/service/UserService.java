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

    public abstract boolean validateUser(String username, String password);

    public abstract boolean isUsernameTaken(String username);

    public abstract User createUser(String username, String password, String nickname);
}

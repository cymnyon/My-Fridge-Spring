package com.myfridge.controller;

import com.myfridge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // Return the signup template
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String nickname) {
        // Create a new user and save it to the database using userService
        // Redirect the user to the login page
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Return the login template
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Authenticate the user using userService
        // Redirect the user to their dashboard or categories page
        return "redirect:/dashboard";
    }

    // Other endpoints...
}
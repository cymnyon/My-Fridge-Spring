package com.myfridge.controller;
import java.util.List;
import java.util.Map;

import com.myfridge.model.User;
import com.myfridge.model.Category;
import com.myfridge.model.Note;
import com.myfridge.service.UserService;
import com.myfridge.service.CategoryService;
import com.myfridge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/dashboard"})
    public String index(Model model) {
        if (userService.isUserLoggedIn()) {
            User user = userService.getCurrentUser();
            model.addAttribute("user", user);
            return "dashboard";
        }
        return "main";
    }

    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signin")
    public String handleSignin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        boolean isExistingUsername = userService.existingUsername(username);
        boolean isValidUser = userService.validateUser(username, password);

        if (!isExistingUsername) {
            redirectAttributes.addAttribute("error", "Username does not exist.");
            return "redirect:/signin";
        } else if (!isValidUser) {
            redirectAttributes.addAttribute("error", "Invalid password.");
            return "redirect:/signin";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username, @RequestParam String password,
                               @RequestParam String nickname, RedirectAttributes redirectAttributes) {
        // Check if the username is already taken
        if (userService.isUsernameTaken(username)) {
            redirectAttributes.addAttribute("error", "Username is already taken.");
            return "redirect:/signup";
        } else {
            // Create a new user and save it to the database
            userService.createUser(username, password, nickname);
            return "redirect:/signin"; // Redirect to login page after successful signup
        }
    }

    @PostMapping("/logout")
    public String handleLogout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the user's session
        HttpSession session = request.getSession(false); // Get the session if it exists, but don't create a new one
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        return "redirect:/dashboard";
    }
}
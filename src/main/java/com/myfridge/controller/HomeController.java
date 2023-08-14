package com.myfridge.controller;
import java.util.List;

import com.myfridge.model.User;
import com.myfridge.model.Category;
import com.myfridge.model.Note;
import com.myfridge.service.UserService;
import com.myfridge.service.CategoryService;
import com.myfridge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", "/dashboard"})
    public String index(Model model) {
        if (userService.isUserLoggedIn()) {
            User user = userService.getCurrentUser();
            model.addAttribute("user", user);

            List<Category> categories = categoryService.getCategoriesForUser(user); // Get user's categories
            model.addAttribute("categories", categories); // Add categories to the model
        }
        return "main";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        // Check if the user exists in the database and validate the password
        if (userService.validateUser(username, password)) {
            // Perform successful login logic here
            return "dashboard";
//            return "redirect:/dashboard"; // Redirect to dashboard or other desired page
        } else {
            // Handle failed login
            return "redirect:/login?error"; // Redirect back to login page with error message
        }
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username, @RequestParam String password, @RequestParam String nickname) {
        // Check if the username is already taken
        if (userService.isUsernameTaken(username)) {
            // Handle username already taken
            return "redirect:/signup?error"; // Redirect back to signup page with error message
        } else {
            // Create a new user and save it to the database
            userService.createUser(username, password, nickname);

            // Perform successful signup logic here
            return "redirect:/contextPath/login"; // Redirect to login page after successful signup
        }
    }

    // Example of POST endpoint for adding text
    @PostMapping("/add_text")
    public String addText(@RequestParam("category_id") Long categoryId,
                          @RequestParam("text") String text,
                          RedirectAttributes redirectAttributes) {
        return "redirect:/category_notes";
    }
}
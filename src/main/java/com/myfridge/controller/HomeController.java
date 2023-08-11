package com.myfridge.controller;
import java.util.List;

import com.myfridge.model.User;
import com.myfridge.model.Category;
import com.myfridge.model.Note;
import com.myfridge.service.UserService;
import com.myfridge.service.CategoryService;
import com.myfridge.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/main")
    public String index(Model model) {
        if (userService.isUserLoggedIn()) {
            User user = userService.getCurrentUser();
            model.addAttribute("user", user);
        }
        return "main";
    }

    @RequestMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    // Example of POST endpoint for adding text
    @PostMapping("/add_text")
    public String addText(@RequestParam("category_id") Long categoryId,
                          @RequestParam("text") String text,
                          RedirectAttributes redirectAttributes) {
        return "redirect:/category_notes";
    }
}
package com.myfridge.controller;

import com.myfridge.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/{noteId}")
    public String viewNote(@PathVariable Long noteId, Model model) {
        // Fetch the note by ID using noteService
        // Pass the note to the template
        return "note"; // Return the note template
    }

    // Other endpoints...
}

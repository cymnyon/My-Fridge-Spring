package com.myfridge.service;

import com.myfridge.model.Note;
import com.myfridge.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class NoteService {

    private NoteRepository noteRepository = null;

    @Autowired
    public NoteService() {
        this.noteRepository = noteRepository;
    }

    public abstract Note createNote(Note newNote);

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElse(null);
    }

    // Add more methods as needed for note-related operations

}
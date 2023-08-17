package com.myfridge.service.impl;

import com.myfridge.model.Note;
import com.myfridge.repository.NoteRepository;
import com.myfridge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        super();
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(Note newNote) {
        return noteRepository.save(newNote);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId).orElse(null);
    }

}
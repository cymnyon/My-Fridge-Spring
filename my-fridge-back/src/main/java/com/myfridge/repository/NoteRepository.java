package com.myfridge.repository;

import com.myfridge.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCategoryId(Long categoryId);
    Note findByNoteId(Long noteId);
    Note findByNoteTitle(String title);
}
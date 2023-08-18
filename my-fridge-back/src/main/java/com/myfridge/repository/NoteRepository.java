package com.myfridge.repository;

import com.myfridge.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCategoryId(Long categoryId);
    Optional<Note> findById(Long id);
    Note findByNoteTitle(String noteTitle);
}
package xyz.stasiak.noteapp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    List<Note> findAll();

    Page<Note> findAll(Pageable pageable);

    Optional<Note> findById(Integer id);

    boolean existsById(Integer id);

    Task save(Task task);
}

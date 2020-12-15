package xyz.stasiak.noteapp.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.stasiak.noteapp.model.Note;
import xyz.stasiak.noteapp.model.NoteRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping(path = "/notes", params = {"!size", "!sort", "!page"})
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteRepository.findAll());
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(Pageable pageable) {
        return ResponseEntity.ok(noteRepository.findAll(pageable)
                                               .getContent());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id) {
        return noteRepository.findById(id)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound()
                                                   .build());
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> addNote(@RequestBody @Valid Note note) {
        Note saved = noteRepository.save(note);
        return ResponseEntity.created(URI.create("/" + saved.getId()))
                             .body(saved);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@RequestBody @Valid Note toUpdate,
                                        @PathVariable int id) {
        if (!noteRepository.existsById(id)) {
            return ResponseEntity.notFound()
                                 .build();
        }
        toUpdate.setId(id);
        noteRepository.save(toUpdate);
        return ResponseEntity.noContent()
                             .build();
    }
}

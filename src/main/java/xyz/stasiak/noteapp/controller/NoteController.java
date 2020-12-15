package xyz.stasiak.noteapp.controller;

import xyz.stasiak.noteapp.model.NoteRepository;

public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}

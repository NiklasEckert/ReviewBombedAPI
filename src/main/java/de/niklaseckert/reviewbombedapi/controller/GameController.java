package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.GameNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Developer;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.repos.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameRepository repository;

    @GetMapping
    public List<Game> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Game one(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game;
    }

    @GetMapping("/{id}/developers")
    public List<Developer> allDevelopers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game.getDevelopers();
    }

    @GetMapping("/{id}/publishers")
    public List<Publisher> allPublishers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game.getPublishers();
    }
}

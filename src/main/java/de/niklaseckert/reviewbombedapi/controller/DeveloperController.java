package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.DeveloperNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Developer;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.repos.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperRepository repository;

    @GetMapping
    public List<Developer> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Developer one(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));

        return developer;
    }

    @GetMapping("/{id}/games")
    public List<Game> allGames(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));

        return developer.getGames();
    }
}

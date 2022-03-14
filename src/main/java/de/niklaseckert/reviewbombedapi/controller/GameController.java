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

/**
 * Controller which handles all {@link Game Game} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/games")
public class GameController {

    /** Repository which contains the {@link Game Games}. */
    private final GameRepository repository;

    /**
     * Get Mapping for all {@link Game Games}.
     *
     * @return a list of all {@link Game Game}.
     */
    @GetMapping
    public List<Game> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link Game Game}.
     *
     * @param id contains the id of a {@link Game Game}.
     * @return the specific {@link Game Game}.
     */
    @GetMapping("/{id}")
    public Game one(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game;
    }

    /**
     * Get Mapping for all {@link Developer Developers} of a specific {@link Game Game}.
     *
     * @param id contains the id of a {@link Game Game}.
     * @return a list of {@link Developer Developers} that developed the {@link Game Game}.
     */
    @GetMapping("/{id}/developers")
    public List<Developer> allDevelopers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game.getDevelopers();
    }

    /**
     * Get Mapping for all {@link Publisher Publisher} of a specific {@link Game Game}.
     *
     * @param id contains the id of a {@link Game Game}.
     * @return a list of {@link Publisher Publisher} that developed the {@link Game Game}.
     */
    @GetMapping("/{id}/publishers")
    public List<Publisher> allPublishers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return game.getPublishers();
    }
}

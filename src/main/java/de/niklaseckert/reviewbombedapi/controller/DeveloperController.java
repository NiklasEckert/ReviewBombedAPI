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

/**
 * Controller which handles all {@link Developer Developer} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {

    /** Repository which contains the {@link Developer Developers}. */
    private final DeveloperRepository repository;

    /**
     * Get Mapping for all {@link Developer Developers}.
     *
     * @return a list of all {@link Developer Developers}.
     */
    @GetMapping
    public List<Developer> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link Developer Developer}.
     *
     * @param id contains the id of the a {@link Developer Developer}.
     * @return the specific {@link Developer Developer}.
     */
    @GetMapping("/{id}")
    public Developer one(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));

        return developer;
    }

    /**
     * Get Mapping for all {@link Game Games} of a specific {@link Developer Developer}.
     *
     * @param id id contains the id of the a {@link Developer Developer}.
     * @return a list of all {@link Game Games} the {@link Developer Developer} took part in.
     */
    @GetMapping("/{id}/games")
    public List<Game> allGames(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));

        return developer.getGames();
    }
}

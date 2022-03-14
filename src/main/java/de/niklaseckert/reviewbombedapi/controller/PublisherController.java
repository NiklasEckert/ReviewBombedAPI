package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.PublisherNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.repos.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller which handles all {@link Publisher Publisher} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {

    /** Repository which contains the {@link Publisher Publishers}. */
    private final PublisherRepository repository;

    /**
     * Get Mapping for all {@link Publisher Publishers}.
     *
     * @return a list of all {@link Publisher Publishers}.
     */
    @GetMapping
    public List<Publisher> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link Publisher Publisher}.
     *
     * @param id contains the id of a {@link Publisher Publisher}.
     * @return a {@link Publisher Publisher}.
     */
    @GetMapping("/{id}")
    public Publisher one(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));

        return publisher;
    }

    /**
     * Get Mapping for all {@link Game Games} of a specific {@link Publisher Publisher}.
     *
     * @param id id contains the id of the a {@link Publisher Publisher}.
     * @return a list of all {@link Game Games} the {@link Publisher} took part in.
     */
    @GetMapping("/{id}/games")
    public List<Game> allGames(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));

        return publisher.getGames();
    }
}

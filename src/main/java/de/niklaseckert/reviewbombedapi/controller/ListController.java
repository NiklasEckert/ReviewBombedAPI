package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.ListNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.repos.ListRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller which handles all {@link ListModel List Model} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/lists")
public class ListController {

    /** Repository which contains the {@link ListModel List Models}. */
    private final ListRepository repository;

    /**
     * Get Mapping for all {@link ListModel List Models}.
     *
     * @return a list of all {@link ListModel List Models}.
     */
    @GetMapping
    public List<ListModel> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link ListModel List Model}.
     *
     * @param id contains the id of a {@link ListModel List Model}.
     * @return a {@link ListModel List Model}.
     */
    @GetMapping("/{id}")
    public ListModel one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ListNotFoundException(id));
    }

    /**
     * Get Mapping for all {@link Game Games} of a specific {@link ListModel List Model}.
     *
     * @param id contains the id of a {@link ListModel}.
     * @return a list of {@link Game Games} that correspond to the specific {@link ListModel List Model}.
     */
    @GetMapping("/{id}/games")
    public List<Game> allGamesOfList(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ListNotFoundException(id)).getGames();
    }
}

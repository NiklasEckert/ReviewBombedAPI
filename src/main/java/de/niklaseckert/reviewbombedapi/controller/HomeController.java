package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.repos.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller which handles all requests for the home page.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    /** Repository which contains the {@link Game Games}. */
    private final GameRepository gameRepository;

    /**
     * Get Mapping for all {@link Game Games} that are currently played.
     *
     * @return a list of all corresponding {@link Game Games}.
     */
    @GetMapping("/currently-playing")
    public List<Game> currentlyPlaying() {
        return gameRepository.findAll();
    }

    /**
     * Get Mapping for all {@link Game Games} that friends currently play.
     *
     * @return a list of all corresponding {@link Game Games}.
     */
    @GetMapping("/friends-playing")
    public List<Game> friendsPlaying() {
        return gameRepository.findAllByTitle("Assassin's Creed Valhalla");
    }

    /**
     * Get Mapping for all {@link Game Games} that friends finished.
     *
     * @return a list of all corresponding {@link Game Games}.
     */
    @GetMapping("/friends-finished")
    public List<Game> friendsFinished() {
        return gameRepository.findAllByTitle("Assassin's Creed IV: Black Flag");
    }
}

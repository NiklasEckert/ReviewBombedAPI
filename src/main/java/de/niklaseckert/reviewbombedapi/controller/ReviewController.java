package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.GameNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.ReviewNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Review;
import de.niklaseckert.reviewbombedapi.model.User;
import de.niklaseckert.reviewbombedapi.repos.GameRepository;
import de.niklaseckert.reviewbombedapi.repos.ReviewRepository;
import de.niklaseckert.reviewbombedapi.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller which handles all {@link Review Review} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    /** Repository which contains the {@link Review Reviews}. */
    private final ReviewRepository repository;

    /** Repository which contains the {@link User Users}. */
    private final UserRepository userRepository;

    /** Repository which contains the {@link Game Games}. */
    private final GameRepository gameRepository;

    /**
     * Get Mapping for all {@link Review Reviews}.
     *
     * @return a list of all {@link Review Reviews}.
     */
    @GetMapping
    public List<Review> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link Review Review}.
     *
     * @param id contains the id of a {@link Review Review}.
     * @return a {@link Review}.
     */
    @GetMapping("/{id}")
    public Review one(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    /**
     * Post Mapping to create a new {@link Review Review}.
     *
     * @param review contains the {@link Review Review} which should be created.
     * @param gameId contains the of the {@link Game Game} the {@link Review Review} corresponds to.
     * @return the created {@link Review Review}.
     */
    @PostMapping
    public Review post(@RequestBody Review review, @RequestHeader("gameId") Long gameId) {
        User user = userRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException(""));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        review.setUser(user);
        review.setGame(game);

        return repository.save(review);
    }

    /**
     * Delete mapping for a {@link Review Review}.
     *
     * @param reviewId contains the id of the {@link Review} which should be deleted.
     */
    @DeleteMapping
    public void delete(@RequestHeader("reviewId") Long reviewId) {
        repository.deleteById(reviewId);
    }
}

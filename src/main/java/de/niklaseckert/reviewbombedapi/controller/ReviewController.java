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

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @GetMapping
    public List<Review> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Review one(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @PostMapping
    public Review post(@RequestBody Review review, @RequestHeader("gameId") Long gameId) {
        User user = userRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException(""));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));

        review.setUser(user);
        review.setGame(game);

        return repository.save(review);
    }
}

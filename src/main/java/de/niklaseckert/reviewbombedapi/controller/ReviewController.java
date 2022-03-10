package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.ReviewNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Review;
import de.niklaseckert.reviewbombedapi.repos.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository repository;

    @GetMapping
    public List<Review> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Review one(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }
}

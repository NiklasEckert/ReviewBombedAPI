package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.DiaryEntryNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.RatingNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.ReviewNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.UserNotFoundException;
import de.niklaseckert.reviewbombedapi.model.*;
import de.niklaseckert.reviewbombedapi.repos.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public List<User> all() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user;
    }

    @GetMapping("/{id}/lists")
    public List<ListModel> allListsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getLists();
    }

    @GetMapping("/{id}/ratings")
    public List<Rating> allRatingsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getRatings();
    }

    @GetMapping("/{id}/ratings/{rId}")
    public Rating oneRating(@PathVariable Long id, @PathVariable Long rId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Rating rating = user.getRatings().stream()
                .filter(r -> r.getId().equals(rId))
                .findFirst()
                .orElseThrow(() -> new RatingNotFoundException(rId));

        return rating;
    }

    @GetMapping("/{id}/diary")
    public List<DiaryEntry> diaryOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getDiary();
    }

    @GetMapping("/{id}/diary/{deId}")
    public DiaryEntry oneDiaryEntry(@PathVariable Long id, @PathVariable Long deId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        DiaryEntry diaryEntry = user.getDiary().stream()
                .filter(r -> r.getId().equals(deId))
                .findFirst()
                .orElseThrow(() -> new DiaryEntryNotFoundException(deId));

        return diaryEntry;
    }

    @GetMapping("/{id}/reviews")
    public List<Review> allReviewsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getReviews();
    }

    @GetMapping("/{id}/reviews/{rId}")
    public Review oneReview(@PathVariable Long id, @PathVariable Long rId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Review review = user.getReviews().stream()
                .filter(r -> r.getId().equals(rId))
                .findFirst()
                .orElseThrow(() -> new ReviewNotFoundException(rId));

        return review;
    }
}

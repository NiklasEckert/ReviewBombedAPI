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

/**
 * Controller which handles all {@link User User} requests.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    /** Repository which contnains the {@link User Users} */
    private final UserRepository repository;

    /**
     * Get Mapping for all {@link User Users}.
     *
     * @return a list of all {@link User Users}.
     */
    @GetMapping
    public List<User> all() {
        return repository.findAll();
    }

    /**
     * Get Mapping for a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @return a {@link User User}.
     */
    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user;
    }

    /**
     * Get Mapping for all {@link ListModel List Model} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @return a list of all {@link ListModel List Models} corresponding to the {@link User User}.
     */
    @GetMapping("/{id}/lists")
    public List<ListModel> allListsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getLists();
    }

    /**
     * Get Mapping for all {@link Rating Ratings} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @return a list of all {@link Rating Ratings} corresponding to the {@link User User}.
     */
    @GetMapping("/{id}/ratings")
    public List<Rating> allRatingsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getRatings();
    }

    /**
     * Get Mapping for a specific {@link Rating Rating} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @param rId contains the id of a {@link Rating Rating}.
     * @return the specific {@link Rating Rating}.
     */
    @GetMapping("/{id}/ratings/{rId}")
    public Rating oneRating(@PathVariable Long id, @PathVariable Long rId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Rating rating = user.getRatings().stream()
                .filter(r -> r.getId().equals(rId))
                .findFirst()
                .orElseThrow(() -> new RatingNotFoundException(rId));

        return rating;
    }

    /**
     * Get Mapping for all {@link DiaryEntry Diary Entries} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @return a list of all {@link DiaryEntry Diary Entries} corresponding to the {@link User User}.
     */
    @GetMapping("/{id}/diary")
    public List<DiaryEntry> diaryOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getDiary();
    }

    /**
     * Get Mapping for a specific {@link DiaryEntry Diary Entry} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @param deId contains the id of a {@link DiaryEntry Diary Entry}.
     * @return the specific {@link DiaryEntry Diary Entry}.
     */
    @GetMapping("/{id}/diary/{deId}")
    public DiaryEntry oneDiaryEntry(@PathVariable Long id, @PathVariable Long deId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        DiaryEntry diaryEntry = user.getDiary().stream()
                .filter(r -> r.getId().equals(deId))
                .findFirst()
                .orElseThrow(() -> new DiaryEntryNotFoundException(deId));

        return diaryEntry;
    }

    /**
     * Get Mapping for all {@link Review Reviews} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @return a list of all {@link Review Reviews} corresponding to the {@link User User}.
     */
    @GetMapping("/{id}/reviews")
    public List<Review> allReviewsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user.getReviews();
    }

    /**
     * Get Mapping for a specific {@link Review Review} of a specific {@link User User}.
     *
     * @param id contains the id of a {@link User User}.
     * @param rId contains the id of a {@link Review Review}.
     * @return the specific {@link Review Review}.
     */
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

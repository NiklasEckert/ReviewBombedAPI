package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.DiaryEntryNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.RatingNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.ReviewNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.UserNotFoundException;
import de.niklaseckert.reviewbombedapi.model.*;
import de.niklaseckert.reviewbombedapi.model.assembler.*;
import de.niklaseckert.reviewbombedapi.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;
    private final ListModelAssembler listModelAssembler;
    private final RatingModelAssembler ratingModelAssembler;
    private final DiaryEntryModelAssembler diaryEntryModelAssembler;
    private final ReviewModelAssembler reviewModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @GetMapping("/{id}/lists")
    public CollectionModel<EntityModel<ListModel>> allListsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<EntityModel<ListModel>> lists = user.getLists().stream()
                .map(listModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(lists,
                    linkTo(methodOn(UserController.class).allListsOfUser(user.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).one(user.getId())).withRel("user")
                );
    }

    @GetMapping("/{id}/ratings")
    public CollectionModel<EntityModel<Rating>> allRatingsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<EntityModel<Rating>> ratings = user.getRatings().stream()
                .map(ratingModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(ratings,
                    linkTo(methodOn(UserController.class).allRatingsOfUser(user.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).one(user.getId())).withRel("user")
                );
    }

    @GetMapping("/{id}/ratings/{rId}")
    public EntityModel<Rating> oneRating(@PathVariable Long id, @PathVariable Long rId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Rating rating = user.getRatings().stream()
                .filter(r -> r.getId().equals(rId))
                .findFirst()
                .orElseThrow(() -> new RatingNotFoundException(rId));
        return ratingModelAssembler.toModel(rating);
    }

    @GetMapping("/{id}/diary")
    public CollectionModel<EntityModel<DiaryEntry>> diaryOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<EntityModel<DiaryEntry>> diary = user.getDiary().stream()
                .map(diaryEntryModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(diary,
                linkTo(methodOn(UserController.class).diaryOfUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).one(user.getId())).withRel("user")
        );
    }

    @GetMapping("/{id}/diary/{deId}")
    public EntityModel<DiaryEntry> oneDiaryEntry(@PathVariable Long id, @PathVariable Long deId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        DiaryEntry diaryEntry = user.getDiary().stream()
                .filter(r -> r.getId().equals(deId))
                .findFirst()
                .orElseThrow(() -> new DiaryEntryNotFoundException(deId));
        return diaryEntryModelAssembler.toModel(diaryEntry);
    }

    @GetMapping("/{id}/reviews")
    public CollectionModel<EntityModel<Review>> allReviewsOfUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<EntityModel<Review>> reviews = user.getReviews().stream()
                .map(reviewModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(reviews,
                linkTo(methodOn(UserController.class).diaryOfUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).one(user.getId())).withRel("user")
        );
    }

    @GetMapping("/{id}/reviews/{rId}")
    public EntityModel<Review> oneReview(@PathVariable Long id, @PathVariable Long rId) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Review review = user.getReviews().stream()
                .filter(r -> r.getId().equals(rId))
                .findFirst()
                .orElseThrow(() -> new ReviewNotFoundException(rId));
        return reviewModelAssembler.toModel(review);
    }
}

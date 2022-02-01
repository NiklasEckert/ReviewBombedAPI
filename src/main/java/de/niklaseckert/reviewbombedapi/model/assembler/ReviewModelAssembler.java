package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.GameController;
import de.niklaseckert.reviewbombedapi.controller.UserController;
import de.niklaseckert.reviewbombedapi.model.Review;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @NonNull
    @Override
    public EntityModel<Review> toModel(@NonNull Review review) {
        return EntityModel.of(review,
                    linkTo(methodOn(UserController.class).oneReview(review.getUser().getId(), review.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).allReviewsOfUser(review.getUser().getId())).withRel("reviews"),
                    linkTo(methodOn(UserController.class).one(review.getUser().getId())).withRel("user"),
                    linkTo(methodOn(GameController.class).one(review.getGame().getId())).withRel("game")
                );
    }
}

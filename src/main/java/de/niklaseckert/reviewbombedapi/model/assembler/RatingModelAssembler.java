package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.GameController;
import de.niklaseckert.reviewbombedapi.controller.UserController;
import de.niklaseckert.reviewbombedapi.model.Rating;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RatingModelAssembler implements RepresentationModelAssembler<Rating, EntityModel<Rating>> {

    @NonNull
    @Override
    public EntityModel<Rating> toModel(@NonNull Rating rating) {
        return EntityModel.of(rating,
                    linkTo(methodOn(UserController.class).oneRating(rating.getUser().getId(), rating.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).allRatingsOfUser(rating.getUser().getId())).withRel("ratings"),
                    linkTo(methodOn(UserController.class).one(rating.getUser().getId())).withRel("user"),
                    linkTo(methodOn(GameController.class).one(rating.getGame().getId())).withRel("game")
                );
    }
}

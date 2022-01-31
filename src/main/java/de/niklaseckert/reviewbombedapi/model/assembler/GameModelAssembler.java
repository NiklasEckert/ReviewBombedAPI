package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.GameController;
import de.niklaseckert.reviewbombedapi.model.Game;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {

    @NonNull
    @Override
    public EntityModel<Game> toModel(@NonNull Game game) {
        return EntityModel.of(game,
                    linkTo(methodOn(GameController.class).one(game.getId())).withSelfRel(),
                    linkTo(methodOn(GameController.class).all()).withRel("games"),
                    linkTo(methodOn(GameController.class).allDevelopers(game.getId())).withRel("allDevelopers"),
                    linkTo(methodOn(GameController.class).allPublishers(game.getId())).withRel("allPublishers")
                );
    }
}

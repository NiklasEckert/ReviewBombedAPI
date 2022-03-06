package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.DeveloperController;
import de.niklaseckert.reviewbombedapi.model.Developer;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeveloperModelAssembler implements RepresentationModelAssembler<Developer, EntityModel<Developer>> {

    @NonNull
    @Override
    public EntityModel<Developer> toModel(@NonNull Developer developer) {
        return EntityModel.of(developer,
                    linkTo(methodOn(DeveloperController.class).one(developer.getId())).withSelfRel(),
                    linkTo(methodOn(DeveloperController.class).all()).withRel("developers"),
                    linkTo(methodOn(DeveloperController.class).allGames(developer.getId())).withRel("games")
                );
    }
}

package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.PublisherController;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PublisherModelAssembler implements RepresentationModelAssembler<Publisher, EntityModel<Publisher>> {

    @NonNull
    @Override
    public EntityModel<Publisher> toModel(@NonNull Publisher publisher) {
        return EntityModel.of(publisher,
                    linkTo(methodOn(PublisherController.class).one(publisher.getId())).withSelfRel(),
                    linkTo(methodOn(PublisherController.class).all()).withRel("publishers"),
                    linkTo(methodOn(PublisherController.class).allGames(publisher.getId())).withRel("allGames")
                );
    }
}

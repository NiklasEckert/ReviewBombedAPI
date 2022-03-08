package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.ListController;
import de.niklaseckert.reviewbombedapi.controller.UserController;
import de.niklaseckert.reviewbombedapi.model.ListModel;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ListModelAssembler implements RepresentationModelAssembler<ListModel, EntityModel<ListModel>> {

    @NonNull
    @Override
    public EntityModel<ListModel> toModel(@NonNull ListModel list) {
        return EntityModel.of(list,
                    linkTo(methodOn(ListController.class).one(list.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).one(list.getUser().getId())).withRel("user"),
                    linkTo(methodOn(UserController.class).allListsOfUser(list.getUser().getId())).withRel("allListsOfUser")
                );
    }

}

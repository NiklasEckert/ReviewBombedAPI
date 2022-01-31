package de.niklaseckert.reviewbombedapi.model.assembler;

import de.niklaseckert.reviewbombedapi.controller.UserController;
import de.niklaseckert.reviewbombedapi.model.User;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @NonNull
    @Override
    public EntityModel<User> toModel(@NonNull User user) {
        return EntityModel.of(user,
                    linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).all()).withRel("users"),
                    linkTo(methodOn(UserController.class).allListsOfUser(user.getId())).withRel("allListsOfUser")
                );
    }
}

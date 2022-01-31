package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.ListNotFoundException;
import de.niklaseckert.reviewbombedapi.controller.exception.UserNotFoundException;
import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.model.User;
import de.niklaseckert.reviewbombedapi.model.assembler.ListModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.UserModelAssembler;
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
}

package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.GameNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Developer;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.model.assembler.DeveloperModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.GameModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.PublisherModelAssembler;
import de.niklaseckert.reviewbombedapi.repos.GameRepository;
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
@RequestMapping("/games")
public class GameController {

    private final GameRepository repository;
    private final GameModelAssembler assembler;
    private final DeveloperModelAssembler developerModelAssembler;
    private final PublisherModelAssembler publisherModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Game>> all() {
        List<EntityModel<Game>> games = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(games, linkTo(methodOn(GameController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Game> one(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));

        return assembler.toModel(game);
    }

    @GetMapping("/{id}/developers")
    public CollectionModel<EntityModel<Developer>> allDevelopers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        List<EntityModel<Developer>> developers = game.getDevelopers().stream()
                .map(developerModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(developers, linkTo(methodOn(GameController.class).one(game.getId())).withSelfRel());
    }

    @GetMapping("/{id}/publishers")
    public CollectionModel<EntityModel<Publisher>> allPublishers(@PathVariable Long id) {
        Game game = repository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        List<EntityModel<Publisher>> publishers = game.getPublishers().stream()
                .map(publisherModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(publishers, linkTo(methodOn(GameController.class).one(game.getId())).withSelfRel());
    }
}

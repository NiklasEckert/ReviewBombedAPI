package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.PublisherNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.model.assembler.GameModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.PublisherModelAssembler;
import de.niklaseckert.reviewbombedapi.repos.PublisherRepository;
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
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherRepository repository;
    private final PublisherModelAssembler assembler;
    private final GameModelAssembler gameModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Publisher>> all() {
        List<EntityModel<Publisher>> publishers = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(publishers, linkTo(methodOn(DeveloperController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Publisher> one(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));

        return assembler.toModel(publisher);
    }

    @GetMapping("/{id}/games")
    public CollectionModel<EntityModel<Game>> allGames(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        List<EntityModel<Game>> games = publisher.getGames().stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(games, linkTo(methodOn(DeveloperController.class).one(publisher.getId())).withSelfRel());
    }
}

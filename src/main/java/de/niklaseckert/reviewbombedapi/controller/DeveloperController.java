package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.DeveloperNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Developer;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.assembler.DeveloperModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.GameModelAssembler;
import de.niklaseckert.reviewbombedapi.repos.DeveloperRepository;
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
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperRepository repository;
    private final DeveloperModelAssembler assembler;
    private final GameModelAssembler gameModelAssembler;

//    @GetMapping
//    public CollectionModel<EntityModel<Developer>> all() {
//        List<EntityModel<Developer>> developers = repository.findAll().stream()
//                .map(assembler::toModel)
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(developers, linkTo(methodOn(DeveloperController.class).all()).withSelfRel());
//    }

    @GetMapping
    public List<Developer> all() {
        return repository.findAll();
    }

//    @GetMapping("/{id}")
//    public EntityModel<Developer> one(@PathVariable Long id) {
//        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));
//
//        return assembler.toModel(developer);
//    }

    @GetMapping("/{id}")
    public Developer one(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));

        return developer;
    }

    @GetMapping("/{id}/games")
    public CollectionModel<EntityModel<Game>> allGames(@PathVariable Long id) {
        Developer developer = repository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));
        List<EntityModel<Game>> games = developer.getGames().stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(games, linkTo(methodOn(DeveloperController.class).one(developer.getId())).withSelfRel());
    }
}

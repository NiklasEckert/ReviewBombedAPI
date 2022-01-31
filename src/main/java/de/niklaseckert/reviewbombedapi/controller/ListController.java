package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.ListNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.model.assembler.GameModelAssembler;
import de.niklaseckert.reviewbombedapi.model.assembler.ListModelAssembler;
import de.niklaseckert.reviewbombedapi.repos.ListRepository;
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
@RequestMapping("/lists")
public class ListController {

    private final ListRepository repository;
    private final ListModelAssembler assembler;
    private final GameModelAssembler gameModelAssembler;

    @GetMapping("/{id}")
    public EntityModel<ListModel> one(@PathVariable Long id) {
        ListModel list = repository.findById(id).orElseThrow(() -> new ListNotFoundException(id));
        return assembler.toModel(list);
    }

    @GetMapping("/{id}/games")
    public CollectionModel<EntityModel<Game>> allGamesOfList(@PathVariable Long id) {
        ListModel list = repository.findById(id).orElseThrow(() -> new ListNotFoundException(id));
        List<EntityModel<Game>> games = list.getGames().stream()
                .map(gameModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(games,
                    linkTo(methodOn(ListController.class).allGamesOfList(list.getId())).withSelfRel(),
                    linkTo(methodOn(ListController.class).one(list.getId())).withRel("list")
                );
    }
}

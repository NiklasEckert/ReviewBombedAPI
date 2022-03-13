package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.ListNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.repos.ListRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lists")
public class ListController {

    private final ListRepository repository;

    @GetMapping
    public List<ListModel> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ListModel one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ListNotFoundException(id));
    }

    @GetMapping("/{id}/games")
    public List<Game> allGamesOfList(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ListNotFoundException(id)).getGames();
    }
}

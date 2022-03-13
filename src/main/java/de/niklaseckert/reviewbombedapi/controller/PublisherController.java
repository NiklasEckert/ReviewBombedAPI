package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.controller.exception.PublisherNotFoundException;
import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.repos.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherRepository repository;

    @GetMapping
    public List<Publisher> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Publisher one(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));

        return publisher;
    }

    @GetMapping("/{id}/games")
    public List<Game> allGames(@PathVariable Long id) {
        Publisher publisher = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));


        return publisher.getGames();
    }
}

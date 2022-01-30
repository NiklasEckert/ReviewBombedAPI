package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.model.Publisher;
import de.niklaseckert.reviewbombedapi.repos.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherRepository repository;

    @GetMapping
    public List<Publisher> all() {
        return repository.findAll();
    }
}

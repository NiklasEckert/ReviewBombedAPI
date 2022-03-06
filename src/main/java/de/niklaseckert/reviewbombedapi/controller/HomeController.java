package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.model.Game;
import de.niklaseckert.reviewbombedapi.repos.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final GameRepository gameRepository;

    @GetMapping
    public List<Game> all() {
        return gameRepository.findAll();
    }
}

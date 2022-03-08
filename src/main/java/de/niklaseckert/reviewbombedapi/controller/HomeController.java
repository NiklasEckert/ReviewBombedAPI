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

    @GetMapping("/currently-playing")
    public List<Game> currentlyPlaying() {
        return gameRepository.findAll();
    }

    @GetMapping("/friends-playing")
    public List<Game> friendsPlaying() {
        return gameRepository.findAllByTitle("Assassin's Creed Valhalla");
    }

    @GetMapping("/friends-finished")
    public List<Game> friendsFinished() {
        return gameRepository.findAllByTitle("Assassin's Creed IV: Black Flag");
    }
}

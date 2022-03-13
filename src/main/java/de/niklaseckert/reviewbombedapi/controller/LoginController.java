package de.niklaseckert.reviewbombedapi.controller;

import de.niklaseckert.reviewbombedapi.model.User;
import de.niklaseckert.reviewbombedapi.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private UserRepository repository;

    @GetMapping("/login")
    public User login() {
        return repository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}

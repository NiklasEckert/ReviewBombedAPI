package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
    List<Game> findAllByTitle(String title);
}

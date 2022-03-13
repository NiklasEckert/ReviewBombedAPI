package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository which contains elements of {@link Game Games}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * Method to search a {@link Game Game} by its title.
     *
     * @param title contains the title to search for.
     * @return the {@link Game Game} when it is in the repository.
     */
    Game findByTitle(String title);

    /**
     * Method to search for all {@link Game Games} with the title.
     *
     * @param title contains the title which should be searched for.
     * @return a list of all {@link Game Games} with the title.
     */
    List<Game> findAllByTitle(String title);
}

package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository which contains elements of {@link User Users}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to search a {@link User User} by his email.
     *
     * @param email contains the email which should be searched.
     * @return the {@link User User} when he is in the repository.
     */
    Optional<User> findByEmail(String email);

    /**
     * Method to search a {@link User User} by his name.
     *
     * @param name contains the name which should be searched.
     * @return the {@link User User} when he is in the repository.
     */
    Optional<User> findByName(String name);
}

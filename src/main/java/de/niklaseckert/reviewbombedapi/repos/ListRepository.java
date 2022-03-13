package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository which contains elements of {@link ListModel List Models}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface ListRepository extends JpaRepository<ListModel, Long> {

    /**
     * Method to search {@link ListModel List Models} by its {@link User User}.
     *
     * @param user contains the {@link User} which should be searched.
     * @return a list of {@link ListModel List Models} of the corresponding {@link User User}.
     */
    List<ListModel> findAllByUser(User user);
}

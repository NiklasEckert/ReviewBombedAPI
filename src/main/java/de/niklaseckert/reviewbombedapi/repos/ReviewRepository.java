package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which contains elements of {@link Review Reviews}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}

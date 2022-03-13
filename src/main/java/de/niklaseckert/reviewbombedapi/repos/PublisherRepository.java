package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which contains elements of {@link Publisher Publishers}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

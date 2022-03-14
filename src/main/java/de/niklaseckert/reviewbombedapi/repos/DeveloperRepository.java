package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which contains elements of {@link Developer Developers}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}

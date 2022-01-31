package de.niklaseckert.reviewbombedapi.repos;

import de.niklaseckert.reviewbombedapi.model.ListModel;
import de.niklaseckert.reviewbombedapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListModel, Long> {
    List<ListModel> findAllByUser(User user);
}

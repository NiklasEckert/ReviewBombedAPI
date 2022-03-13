package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class which represents a List Model.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "list")
public class ListModel {

    /** Represents the id of a List Model. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_id_seq")
    @SequenceGenerator(name = "list_id_seq", sequenceName = "list_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the name of a List Model. */
    @Column(name = "name", nullable = false)
    private String name;

    /** Represents the description of a List Model. */
    @Column(name = "description")
    private String description;

    /** Represents the {@link User User} that created the List Model. */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Represents the {@link Game Games} of a List Model. */
    @ManyToMany
    @JoinTable(
            name = "list_games",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    @JsonIgnoreProperties({ "publishers", "developers" })
    private List<Game> games;
}

package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Class which represents a Rating.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "rating")
public class Rating {

    /** Represents the id of a Rating. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_id_seq")
    @SequenceGenerator(name = "rating_id_seq", sequenceName = "rating_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the rating of a Rating. */
    @Column(name = "rate", nullable = false)
    private int rate;

    /** Represents the {@link User User} that created the Rating. */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Represents the {@link Game Game} that the Rating corresponds to. */
    @JsonIgnoreProperties({"id", "date", "description"})
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

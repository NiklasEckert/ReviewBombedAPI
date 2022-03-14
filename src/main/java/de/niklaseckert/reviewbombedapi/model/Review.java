package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class which represents a Review.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "review")
public class Review {

    /** Represents the id of a Review. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
    @SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the title of a Review. */
    @Column(name = "title", nullable = false)
    private String title;

    /** Represents the date the Review got created. */
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

    /** Represents the rating of the Review. */
    @Column(name = "rate", nullable = false)
    private int rate;

    /** Represents the text of the Review. */
    @Column(name = "review_text", nullable = false)
    private String reviewText;

    /** Represents the {@link User User} that created the Review. */
    @JsonIgnoreProperties({"lists", "ratings", "diary", "reviews"})
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Represents the {@link Game Game} corresponding to the Review. */
    @JsonIgnoreProperties({"date", "description", "previewImageUrl", "developers", "publishers", "screenshots"})
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

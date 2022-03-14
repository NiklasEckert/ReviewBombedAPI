package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Class which represents a Screenshot.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "screenshot")
public class Screenshot {

    /** Represents the id of a Screenshot. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screenshot_id_seq")
    @SequenceGenerator(name = "screenshot_id_seq", sequenceName = "screenshot_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the url for the Screenshot image. */
    @Column(name = "screenshot_url", nullable = false)
    private String screenshotUrl;

    /** Represents the {@link Game Game} the Screenshot corresponds to. */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class which represents a Diary Entry.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "diary_entry")
public class DiaryEntry {

    /** Represents the id of a Diary Entry. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diary_entry_id_seq")
    @SequenceGenerator(name = "diary_entry_id_seq", sequenceName = "diary_entry_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the rating of a Diary Entry. */
    @Column(name = "rate", nullable = false)
    private int rate;

    /** Represents the date when the Diary Entry was created. */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /** Represents the {@link User User} of the Diary Entry. */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Represents the {@link Game Game} which the Diary Entry corresponds to. */
    @JsonIgnoreProperties({"id", "date", "description"})
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

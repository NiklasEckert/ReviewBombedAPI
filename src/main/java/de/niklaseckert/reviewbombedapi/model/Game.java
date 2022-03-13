package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Class which represents a Game.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "games")
public class Game {

    /** Represents the id of a Game. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_id_seq")
    @SequenceGenerator(name = "games_id_seq", sequenceName = "games_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the title of a Game. */
    @Column(name = "title", nullable = false)
    private String title;

    /** Represents the publishing date of the Game. */
    @Column(name = "publishing_date", nullable = false)
    private LocalDate date;

    /** Represents the description of the Game. */
    @Column(name = "description")
    private String description;

    /** Represents the url which is used for the Game cover. */
    @Column(name = "cover_url")
    private String coverUrl;

    /** Represents the url which is used for the preview image. */
    @Column(name = "preview_image_url")
    private String previewImageUrl;

    /** Represents the {@link Publisher Publishers} of a Game. */
    @ManyToMany
    @JoinTable(
            name = "games_publisher",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private List<Publisher> publishers;

    /** Represents the {@link Developer Developers} of a Game. */
    @ManyToMany
    @JoinTable(
            name = "games_developer",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    @JsonIgnoreProperties({"games", "publishers"})
    private List<Developer> developers;

    /** Represents the {@link ListModel List Models} that contain the game. */
    @JsonIgnore
    @ManyToMany(mappedBy = "games")
    private List<ListModel> lists;

    /** Represents the {@link Rating Ratings} of a Game. */
    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Rating> ratings;

    /** Represents the {@link DiaryEntry Diary Entries} that contain the Game. */
    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<DiaryEntry> diaryEntries;

    /** Represents the {@link Review Reviews} of the Game. */
    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    /** Represents the {@link Screenshot Screenshots} of the Game. */
    @OneToMany(mappedBy = "game")
    private List<Screenshot> screenshots;
}

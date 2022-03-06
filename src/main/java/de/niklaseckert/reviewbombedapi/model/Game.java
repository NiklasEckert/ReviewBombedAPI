package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_id_seq")
    @SequenceGenerator(name = "games_id_seq", sequenceName = "games_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publishing_date", nullable = false)
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "games_publisher",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
//    @JsonIgnore
    private List<Publisher> publishers;

    @ManyToMany
    @JoinTable(
            name = "games_developer",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
//    @JsonIgnore
    private List<Developer> developers;

    @JsonIgnore
    @ManyToMany(mappedBy = "games")
    private List<ListModel> lists;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Rating> ratings;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<DiaryEntry> diaryEntries;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<Review> reviews;
}

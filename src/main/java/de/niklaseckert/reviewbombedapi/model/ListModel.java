package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "list")
public class ListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_id_seq")
    @SequenceGenerator(name = "list_id_seq", sequenceName = "list_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "list_games",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    @JsonIgnoreProperties({ "publishers", "developers" })
    private List<Game> games;
}

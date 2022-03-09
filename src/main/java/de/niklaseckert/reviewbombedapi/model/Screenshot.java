package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "screenshot")
public class Screenshot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screenshot_id_seq")
    @SequenceGenerator(name = "screenshot_id_seq", sequenceName = "screenshot_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "screenshot_url", nullable = false)
    private String screenshotUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

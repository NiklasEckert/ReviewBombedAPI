package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "diary_entry")
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diary_entry_id_seq")
    @SequenceGenerator(name = "diary_entry_id_seq", sequenceName = "diary_entry_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnoreProperties({"id", "date", "description"})
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

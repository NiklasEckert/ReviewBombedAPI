package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
    @SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnoreProperties({"date", "description", "previewImageUrl", "developers", "publishers", "screenshots"})
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}

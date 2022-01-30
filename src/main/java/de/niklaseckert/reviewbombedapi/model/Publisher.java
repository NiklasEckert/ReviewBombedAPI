package de.niklaseckert.reviewbombedapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_id_seq")
    @SequenceGenerator(name = "publisher_id_seq", sequenceName = "publisher_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "publishers")
    private Set<Game> games;
}

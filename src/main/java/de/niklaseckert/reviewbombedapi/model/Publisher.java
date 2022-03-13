package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class which represents a Publisher.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "publisher")
public class Publisher {

    /** Represents the id of a Publisher. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_id_seq")
    @SequenceGenerator(name = "publisher_id_seq", sequenceName = "publisher_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the name of a Publisher. */
    @Column(name = "name", nullable = false)
    private String name;

    /** Represents the {@link Game Games} of the Developer. */
    @JsonIgnore
    @ManyToMany(mappedBy = "publishers")
    private List<Game> games;
}

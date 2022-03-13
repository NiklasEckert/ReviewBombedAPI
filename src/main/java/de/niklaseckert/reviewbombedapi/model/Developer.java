package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class which represents a Developer.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "developer")
public class Developer {

    /** Represents the id of a Developer. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developer_id_seq")
    @SequenceGenerator(name = "developer_id_seq", sequenceName = "developer_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the name of a Developer. */
    @Column(name = "name", nullable = false)
    private String name;

    /** Represents the {@link Game Games} of a Developer. */
    @JsonIgnoreProperties({"developers", "publishers"})
    @ManyToMany(mappedBy = "developers")
    private List<Game> games;
}

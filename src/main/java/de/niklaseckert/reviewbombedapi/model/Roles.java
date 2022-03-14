package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Class which represents a Role.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "rb_role")
public class Roles implements GrantedAuthority {

    /** Represents the id of a Role. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rb_role_id_seq")
    @SequenceGenerator(name = "rb_role_id_seq", sequenceName = "rb_role_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the name of a Role. */
    @Column(name = "name", nullable = false)
    private String name;

    /** Represents the {@link User Users} that have this Role. */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /**
     * Getter for the authority.
     *
     * @return the name of the authority.
     */
    @Override
    public String getAuthority() {
        return name;
    }
}

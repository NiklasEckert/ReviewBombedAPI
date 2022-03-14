package de.niklaseckert.reviewbombedapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Class which represents a User.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Data
@Entity
@Table(name = "rb_user")
public class User implements UserDetails {

    /** Represents the id of a User. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    /** Represents the username of a User. */
    @Column(name = "name", nullable = false)
    private String name;

    /** Represents the email of a User. */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /** Represents the password of a User. */
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    /** Represents the url for the profile image of the User. */
    @Column(name = "image_url", nullable = false)
    private String profileImageUrl;

    /** Represents the {@link ListModel List Models} the User created. */
    @JsonIgnoreProperties({ "games", "user" })
    @OneToMany(mappedBy = "user")
    private List<ListModel> lists;

    /** Represents the {@link Rating Ratings} the User created. */
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    /** Represents the {@link DiaryEntry Diary Entries} the User created. */
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<DiaryEntry> diary;

    /** Represents the {@link Review Reviews} the User Created. */
    @JsonIgnoreProperties({ "games" })
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    /** Represents the {@link Roles Roles} the User has. */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> roles;

    /**
     * Getter for the {@link Roles Roles}.
     *
     * @return the {@link Roles Roles} of the User.
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * Getter for the username.
     *
     * @return the username of the User.
     */
    @Override
    public String getUsername() {
        return name;
    }

    /**
     * Checks if account is expired.
     *
     * @return true when the Account is not expired, otherwise false.
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if account is locked.
     *
     * @return true when the Account is not locked, otherwise false.
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if credentials are expired.
     *
     * @return  true when the credentials are not expired, otherwise false.
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if account is enabled.
     *
     * @return true when account is enabled, otherwise false.
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}

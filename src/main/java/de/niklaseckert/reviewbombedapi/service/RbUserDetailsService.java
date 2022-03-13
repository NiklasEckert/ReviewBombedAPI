package de.niklaseckert.reviewbombedapi.service;

import de.niklaseckert.reviewbombedapi.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom User Details Service.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Service
@AllArgsConstructor
public class RbUserDetailsService implements UserDetailsService {

    /** Repository which contains {@link de.niklaseckert.reviewbombedapi.model.User Users}. */
    private final UserRepository repository;

    /**
     * Returns a {@link de.niklaseckert.reviewbombedapi.model.User User} by its username.
     *
     * @param username contains name of the {@link de.niklaseckert.reviewbombedapi.model.User User}.
     * @return a {@link de.niklaseckert.reviewbombedapi.model.User User} object.
     * @throws UsernameNotFoundException if the username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}

package de.niklaseckert.reviewbombedapi.config;

import de.niklaseckert.reviewbombedapi.service.RbBasicAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Class which restricts access for the {@link de.niklaseckert.reviewbombedapi.model.User User}.
 *
 * @author Niklas Eckert
 * @author Jakob Friedsam
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userDetailsService;

    /** Checks if a {@link de.niklaseckert.reviewbombedapi.model.User User} is authenticated. */
    private final RbBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * Configures the access.
     *
     * @param http allows configuring web based security for specific http requests.
     * @throws Exception if something goes wrong.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/**").authenticated()
                .antMatchers(HttpMethod.GET, "/login").authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(basicAuthenticationEntryPoint)
                .and()
                .csrf().disable();
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(basicAuthenticationEntryPoint)
//                .and()
//                .csrf().disable();
    }

//    @Bean
//    public BCryptPasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    /**
     * Password encoder which is used to encrypt the passwords.
     *
     * @return a password encoder.
     */
    @Bean
    public NoOpPasswordEncoder getPasswordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}

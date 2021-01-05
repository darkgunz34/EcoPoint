package fr.ecopoint.security;

import fr.ecopoint.model.constante.RoleConstante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class en charge de la sécurité de l'application pour les personnes non authentifier.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/static/js/**").permitAll()
                .antMatchers("/utilisateur/**").hasRole(RoleConstante.USER_ROLE_PAR_DEFAULT)
                .and().logout().permitAll().deleteCookies("JSESSIONID")
                .and().csrf().disable();
    }

    /**
     * Méthode pour hash le mot de passe.
     * @return Le hash.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
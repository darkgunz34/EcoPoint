package fr.ecopoint.security;

import fr.ecopoint.model.constante.RoleConstante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/style/css").permitAll()
                .antMatchers("/static/style/js/**").permitAll()
                .antMatchers("/utilisateur/**").hasRole(RoleConstante.USER_ROLE_PAR_DEFAULT)
                .and().logout().permitAll().deleteCookies("JSESSIONID")
                .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
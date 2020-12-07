package fr.ecopoint.controleur;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Ensemble des routes pouvant être pointer sur l'application.
 */
@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        affichagePageUtilisateur(registry);
    }

    private void affichagePageUtilisateur(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }
}
package fr.ecopoint.web.controleur;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Ensemble des routes pouvant être pointer sur l'application.
 */
@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final @NotNull ViewControllerRegistry registry) {
        this.affichagePageUtilisateur(registry);
    }

    /**
     * Méthode pour la gestion des pages accessible de l'application.
     * @param registry La class où est stocker ces données.
     */
    private void affichagePageUtilisateur(final ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("accueil");
        registry.addViewController("/accueil").setViewName("accueil");
        registry.addViewController("/inscription").setViewName("inscription");
        registry.addViewController("/seconnecter").setViewName("seconnecter");
    }
}
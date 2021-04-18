package fr.ecopoint.web.controleur;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final @NotNull ViewControllerRegistry registry) {
        this.affichagePageUtilisateur(registry);
    }

    private void affichagePageUtilisateur(final ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("accueil");
        registry.addViewController("/accueil").setViewName("accueil");
        registry.addViewController("/inscription").setViewName("inscription");
        registry.addViewController("/seconnecter").setViewName("seconnecter");
        registry.addViewController("/contact").setViewName("contact");
    }
}
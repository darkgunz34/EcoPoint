package fr.ecopoint.web.controleur;

import fr.ecopoint.api.AccessByAPI;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api",
                HandlerTypePredicate.forAnnotation(RestController.class));
    }

    @Override
    public void addViewControllers(final @NotNull ViewControllerRegistry registry) {
        this.affichagePageUtilisateur(registry);
    }

    private void affichagePageUtilisateur(final ViewControllerRegistry registry){
        registry.addRedirectViewController("/","/accueil");
        registry.addViewController("/accueil").setViewName("accueil");
        registry.addViewController("/inscription").setViewName("inscription");
        registry.addViewController("/seconnecter").setViewName("seconnecter");
        registry.addViewController("/sedeconnecter").setViewName("sedeconnecter");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/utilisateur/mesinformations").setViewName("mesinformations");
        registry.addViewController("mesinformations/ConfirmationSupression");
        registry.addViewController("mesinformations/passwordupdate");
    }
}
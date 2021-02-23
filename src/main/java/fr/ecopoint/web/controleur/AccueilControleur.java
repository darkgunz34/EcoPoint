package fr.ecopoint.web.controleur;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.PointException;
import fr.ecopoint.service.PointCollectService;
import fr.ecopoint.web.dto.entities.UserAccueilDto;
import fr.ecopoint.web.dto.entities.factory.FactoryUserAccueilDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Contrôleur d'accueil.
 * GET
 */
@Controller
@RequestMapping("/accueil")
public class AccueilControleur {

    /**
     * La page associé au contrôleur.
     */
    private static final String PAGE = "accueil";

    /**
     * Le logger de la class.
     */
    private static final Logger logger = LogManager.getLogger(AccueilControleur.class);

    /**
     * Traitement des points associés à un utilisateur.
     */
    private final PointCollectService pointCollectService;

    public AccueilControleur(final PointCollectService pointCollectService) {
        this.pointCollectService = pointCollectService;
    }

    /**
     * Méthode pour instancier un userCreateLoginDto.
     * @return Une nouvelle instance.
     */
    @ModelAttribute("userAccueilDto")
    public UserAccueilDto userRegistrationDto(@NotNull final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user != null) {
            int total = 0;
            try{
                total = this.pointCollectService.getPointCollectFromUser(user).stream().mapToInt(PointCollect::getNombrePoint).sum();
            }catch (final PointException e){
                logger.error(e);
            }
            return FactoryUserAccueilDto.getRoleParDefault(user, total);
        } else {
            return null;
        }
    }

    @GetMapping
    public String getAccueil(final Model model, final HttpServletRequest req){
        logger.debug("getAccueil");
        logger.debug(req.getHeader("User-Agent"));
        return PAGE;
    }
}

package fr.ecopoint.web.controleur;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.PointException;
import fr.ecopoint.service.PointCollectService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.UserAccueilDto;
import fr.ecopoint.web.dto.entities.factory.FactoryUserAccueilDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accueil")
public class AccueilControleur {

    private static final Logger logger = LogManager.getLogger(AccueilControleur.class);
    private final PointCollectService pointCollectService;

    public AccueilControleur(final PointCollectService pointCollectService) {
        this.pointCollectService = pointCollectService;
    }

    @ModelAttribute("userAccueilDto")
    public UserAccueilDto userRegistrationDto(final HttpSession session) {
        final User user = (User) session.getAttribute(Constante.USER_SESSION);
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
    public String getAccueil(final Model model, final HttpServletRequest req, final HttpSession session){
        logger.debug("getAccueil");
        logger.debug(req.getHeader("User-Agent"));
        model.addAttribute(Constante.MODEL_MESSAGE,session.getAttribute(Constante.MODEL_MESSAGE));
        session.removeAttribute(Constante.MODEL_MESSAGE);
        return Constante.PAGE_ACCEUIL;
    }
}

package fr.ecopoint.web.controleur;


import fr.ecopoint.web.Constante.Constante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sedeconnecter")
public class SeDeconnecter {

    private static final Logger logger = LogManager.getLogger(SeDeconnecter.class);

    @GetMapping
    public String getSeDeconnecter(final Model model,final HttpSession session) {
        logger.debug("getSeDeconnecter()");
        session.removeAttribute(Constante.USER_SESSION);
        session.setAttribute(Constante.MODEL_MESSAGE, "Vous être à présent déconnecter");
        return Constante.PAGE_REDIRECT_ACCEUIL;
    }
}
package fr.ecopoint.web.controleur;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.service.RoleService;
import fr.ecopoint.service.UserService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.UserLoginDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/seconnecter")
public class SeConnecterControleur {

    private static final Logger logger = LogManager.getLogger(SeConnecterControleur.class);
    private final UserService userService;
    private final RoleService roleService;

    public SeConnecterControleur(final RoleService roleService, final UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }

    @ModelAttribute("userLoginDto")
    public UserLoginDto userRegistrationDto() {
        return new UserLoginDto();
    }

    @GetMapping
    public String getSeConnecter() {
        logger.debug("getSeConnecter()");
        return Constante.PAGE_CONNEXION;
    }

    @PostMapping
    public String postSeConnecter(final Model model, @ModelAttribute("userLoginDto") final UserLoginDto userLoginDto, final HttpSession session) {
        logger.debug("postSeConnecter()");
        boolean valide = true;
        if (userLoginDto.getMotDePasse() == null || userLoginDto.getMotDePasse().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurMotDePasse", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if (userLoginDto.getMail() == null) {
            valide = false;
            model.addAttribute("erreurMail", MessageEx.MESSAGE_EXCEPTION_MAIL);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }

        if (valide) {
            try {
                final Role role = this.recuperationRole();
                User user = FactoryUser.getUserFromLogin(userLoginDto,role);
                user = this.userService.findByMailAndPassword(user);
                if (user!= null) {
                    session.setAttribute(Constante.USER_SESSION, user);
                    session.setAttribute(Constante.MODEL_MESSAGE,"Bienvenue de retour parmis nous !");
                    return Constante.PAGE_REDIRECT_ACCEUIL;
                }
            } catch (final UserException userException) {
                logger.info(userException.getMessage());
                model.addAttribute("erreurMauvaisCompte","Votre identifiant et votre mot de passe ne sont valide. Merci de les ressaisir");
            } catch (final Exception e) {
                model.addAttribute(Constante.ERREUR_INTERNE, "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
                logger.error("Erreur autre :".concat(e.getMessage()));
            }
        }else{
            model.addAttribute("erreurMauvaisCompte","Votre identifiant et votre mot de passe ne sont valide. Merci de les ressaisir");
        }
        return Constante.PAGE_CONNEXION;
    }

    private Role recuperationRole() throws RoleException {
        final Role role = FactoryRole.getRoleParDefault();
        if (this.roleService.exit(role)) {
            return this.roleService.findByName(role.getName());
        } else {
            logger.info("le role avec la valeur par défaut n'existe pas en bdd...");
            return role;
        }
    }
}
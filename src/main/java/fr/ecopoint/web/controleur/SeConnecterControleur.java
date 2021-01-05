package fr.ecopoint.web.controleur;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageException;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.model.service.RoleService;
import fr.ecopoint.model.service.UserService;
import fr.ecopoint.web.dto.entities.UserLoginDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur d'inscription.
 * POST et GET
 */
@Controller
@RequestMapping("/seconnecter")
public class SeConnecterControleur {

    /**
     * La page associé au contrôleur.
     */
    private static final String PAGE = "seconnecter";

    /**
     * Le logger de la class.
     */
    private final static Logger logger = LogManager.getLogger(SeConnecterControleur.class);

    /**
     * Service pour la manipulation des données depuis la BDD pour les users.
     */
    private final UserService userService;

    /**
     * Service pour la manipulation des données depuis la BDD pour les roles.
     */
    private final RoleService roleService;

    /**
     * Constructeur par défaut.
     * @param roleService Service pour la manipulation des données depuis la BDD pour les roles.
     * @param userService Service pour la manipulation des données depuis la BDD pour les users.
     */
    public SeConnecterControleur(final RoleService roleService, final UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }

    /**
     * Méthode pour instancier un userCreateLoginDto.
     * @return Une nouvelle instance.
     */
    @ModelAttribute("userLoginDto")
    public UserLoginDto userRegistrationDto() {
        return new UserLoginDto();
    }

    /**
     * Méthode GET du contrôleur.
     * @return la page.
     */
    @GetMapping
    public String getSeConnecter() {
        logger.debug("getSeConnecter()");
        return PAGE;
    }

    /**
     * Méthode POST du contrôleur.
     * @param model Le model pour y ajouter des données afficher dans la page / la redirection.
     * @param userLoginDto Le user saisie par l'utilisateur.
     * @return L'url identique à l'inscription si une erreur est présente ou redirection vers la page d'accueil.
     */
    @PostMapping
    public String postSeConnecter(final Model model, @ModelAttribute("userLoginDto") final UserLoginDto userLoginDto) {
        logger.debug("postSeConnecter()");
        boolean valide = true;
        if (userLoginDto.getMotDePasse() == null || userLoginDto.getMotDePasse().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurMotDePasse", MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
            logger.debug(MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if (userLoginDto.getMail() == null) {
            valide = false;
            model.addAttribute("erreurMail", MessageException.MESSAGE_EXCEPTION_MAIL);
            logger.debug(MessageException.MESSAGE_EXCEPTION_MAIL);
        }

        if (valide) {
            try {
                final Role role = this.recuperationRole();
                User user = FactoryUser.getUserFromLogin(userLoginDto,role);
                user = this.userService.findByMailAndPassword(user);
                if (user!= null) {
                    model.addAttribute("user",user);
                    return "index";
                }
            } catch (final UserException userException) {
                logger.info(userException.getMessage());
                model.addAttribute("erreurMauvaisCompte","Votre identifiant et votre mot de passe ne sont valide. Merci de les ressaisir");
            } catch (final Exception e) {
                model.addAttribute("erreurInterne", "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
                logger.error("Erreur autre :".concat(e.getMessage()));
            }
        }else{
            model.addAttribute("erreurMauvaisCompte","Votre identifiant et votre mot de passe ne sont valide. Merci de les ressaisir");
        }
        return PAGE;
    }

    /**
     * Méthode pour récupérer le role par défaut depuis la bdd s'il existe déjà.
     * @return le Role par défaut depuis la bdd ou un nouveau si existant.
     * @throws RoleException En cas d'erreur lors du traitement en BDD.
     */
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
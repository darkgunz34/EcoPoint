package fr.ecopoint.web.controleur;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.service.RoleService;
import fr.ecopoint.service.UserService;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contrôleur d'inscription.
 * POST et GET
 */
@Controller
@RequestMapping("/inscription")
public class InscriptionControleur {

    /**
     * La page associé au contrôleur.
     */
    private static final String PAGE = "inscription";

    /**
     * Le logger de la class.
     */
    private static final Logger logger = LogManager.getLogger(InscriptionControleur.class);

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
    public InscriptionControleur(final RoleService roleService, final UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }

    /**
     * Méthode pour instancier un userCreateLoginDto.
     * @return Une nouvelle instance.
     */
    @ModelAttribute("userCreateLoginDto")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    /**
     * Méthode GET du contrôleur.
     * @return la page.
     */
    @GetMapping
    public String getInscription() {
        logger.debug("getInscription()");
        return PAGE;
    }

    /**
     * Méthode POST du contrôleur.
     * @param model Le model pour y ajouter des données afficher dans la page / la redirection.
     * @param userRegistrationDto Le user saisie par l'utilisateur.
     * @return L'url identique à l'inscription si une erreur est présente ou redirection vers la page d'accueil.
     */
    @PostMapping
    public String postInscription(final Model model, @ModelAttribute("userCreateLoginDto") final UserRegistrationDto userRegistrationDto) {
        logger.debug("postInscription()");
        if (this.valideDonnee(model,userRegistrationDto)) {
            try {
                final Role role = this.recuperationRole();
                final User user = FactoryUser.getUserFromCreation(userRegistrationDto,role);
                if (!this.userService.exit(user) && this.userService.save(user)) {
                    model.addAttribute("message", "Consulter vos mails afin d'activer votre compte");
                    return "accueil";
                } else {
                    logger.debug("tentative d'ajout de doublon :");
                    throw new UserException(MessageEx.MESSAGE_EXCEPTION_DOUBLON);
                }
            } catch (final UserException userException) {
                model.addAttribute("erreurDejaExistant", userException.getMessage());
                logger.error("Erreur durant le traitement des données du userException :".concat(userException.getMessage()));
            } catch (final RoleException roleException) {
                model.addAttribute("erreurInterne", "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
                logger.error("Erreur durant la recuperation du role en BDD : ".concat(roleException.getMessage()));
            } catch (final Exception e) {
                model.addAttribute("erreurInterne", "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
                logger.error("Erreur autre :".concat(e.getMessage()));
            }
        }
        model.addAttribute("valeur_mail", userRegistrationDto.getMail());
        model.addAttribute("valeur_telephone", userRegistrationDto.getTelephone());
        model.addAttribute("valeur_nom", userRegistrationDto.getNom());
        model.addAttribute("valeur_prenom", userRegistrationDto.getPrenom());
        model.addAttribute("valeur_adresse", userRegistrationDto.getAdresse());

        return PAGE;
    }

    /**
     * Méthode pour récupérer le role par défaut depuis la bdd s'il existe déjà.
     * @return le Role par défaut depuis la bdd ou un nouveau si existant.
     * @throws RoleException En cas d'erreur lors du traitement en BDD.
     */
    private Role recuperationRole() throws RoleException {
        final Role role = FactoryRole.getRoleParDefault();
        if (Boolean.TRUE.equals(this.roleService.exit(role))) {
            return this.roleService.findByName(role.getName());
        } else {
            logger.info("le role avec la valeur par défaut n'existe pas en bdd...");
            return role;
        }
    }

    private boolean valideDonnee(final Model model, final UserRegistrationDto userRegistrationDto){
        boolean valide = true;
        if (userRegistrationDto.getMotDePasse() == null || userRegistrationDto.getMotDePasse().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurMotDePasse", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if (!userRegistrationDto.getMotDePasse().equals(userRegistrationDto.getMotDePasse2())) {
            valide = false;
            model.addAttribute("erreurMotDePasse2", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        if (userRegistrationDto.getMail() == null || !userRegistrationDto.getMail().matches(UserConstante.REGEX_VALIDATION_MAL)) {
            valide = false;
            model.addAttribute("erreurMail", MessageEx.MESSAGE_EXCEPTION_MAIL);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if (userRegistrationDto.getTelephone() == null || !userRegistrationDto.getTelephone().matches(UserConstante.REGEX_VALIDATION_TEL)) {
            valide = false;
            model.addAttribute("erreurTelephone", MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
        }
        if (userRegistrationDto.getNom() == null || userRegistrationDto.getNom().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurNom", MessageEx.MESSAGE_EXCEPTION_NOM);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_NOM);
        }
        if (userRegistrationDto.getPrenom() == null || userRegistrationDto.getPrenom().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurPrenom", MessageEx.MESSAGE_EXCEPTION_PRENOM);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }
        if (userRegistrationDto.getAdresse() == null || userRegistrationDto.getAdresse().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurAdresse", MessageEx.MESSAGE_EXCEPTION_ADRESSE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_ADRESSE);
        }
        return valide;
    }
}
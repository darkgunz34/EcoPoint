package fr.ecopoint.web.controleur;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MailException;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryAdresse;
import fr.ecopoint.model.factory.FactoryRole;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.model.utils.MailUtile;
import fr.ecopoint.service.RoleService;
import fr.ecopoint.service.UserService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import fr.ecopoint.web.tools.Utils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/inscription")
public class InscriptionControleur {

    @Qualifier("javaMailSender")
    public MailUtile mailUtile;
    private static final Logger logger = LogManager.getLogger(InscriptionControleur.class);
    private final UserService userService;
    private final RoleService roleService;

    public InscriptionControleur(final RoleService roleService, final UserService userService,final MailUtile mailUtile){
        this.roleService = roleService;
        this.userService = userService;
        this.mailUtile = mailUtile;
    }

    @ModelAttribute("userCreateLoginDto")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    private void addContenuParDefault(final Model model){
        model.addAttribute("valeur_mail", "stephan.parichon.pro@gmail.com");
        model.addAttribute("valeur_telephone", "0645606439");
        model.addAttribute("valeur_nom", "Parichon");
        model.addAttribute("valeur_pseudo", "stephan");
        model.addAttribute("valeur_prenom", "Stéphan");
        model.addAttribute("valeur_rue", "84 Allée de Camargue");
        model.addAttribute("valeur_code_postal", "34400");
        model.addAttribute("valeur_ville", "Saint Christol");
    }
    @GetMapping
    public String getInscription(final Model model,final HttpSession session) {
        logger.debug("getInscription()");
        addContenuParDefault(model);
        if(session.getAttribute("user") != null)
            return Constante.PAGE_ACCEUIL;
        else
            return Constante.PAGE_INSCRIPTION;
    }

    @PostMapping
    public String postInscription(final Model model, @ModelAttribute("userCreateLoginDto") final UserRegistrationDto userRegistrationDto, final HttpSession session) {
        logger.debug("postInscription()");
        Utils.valideUserRegistrationDto(model,userRegistrationDto);
        try {
            final Role role = this.recuperationRole();
            final User user = FactoryUser.getUserFromCreation(userRegistrationDto, role);
            final Adresse adresse = FactoryAdresse.getAdresseFromCreation(userRegistrationDto);
            user.setAdresse(adresse);
            if (mailUtile.envoyerMailInscription(user) && !this.userService.exit(user) && this.userService.save(user,true)) {
                session.setAttribute("user", user);
                model.addAttribute("message", "Consulter vos mails afin d'activer votre compte");
                return Constante.PAGE_REDIRECT_ACCEUIL;
            } else {
                logger.debug("tentative d'ajout de doublon :");
                throw new UserException(MessageEx.MESSAGE_EXCEPTION_DOUBLON);
            }
        } catch (final MailException mailException) {
            model.addAttribute(Constante.ERREUR_INTERNE, "Impossible d'envoyer un mail pour confirmer l'inscription. Veuillez réessayer ultérieurement ou contacter directement le support. Veuillez nous excuser de cette gène occasionné.");
            logger.error("Erreur durant l'envoie du mail : ".concat(mailException.getMessage()));
        } catch (final UserException userException) {
            model.addAttribute(Constante.ERREUR_INTERNE, userException.getMessage());
            logger.error("Erreur durant le traitement des données du userException :".concat(userException.getMessage()));
        } catch (final RoleException roleException) {
            model.addAttribute(Constante.ERREUR_INTERNE, "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
            logger.error("Erreur durant la recuperation du role en BDD : ".concat(roleException.getMessage()));
        } catch (final Exception e) {
            model.addAttribute(Constante.ERREUR_INTERNE, "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
            logger.error("Erreur autre :".concat(e.getMessage()));
        }
        model.addAttribute("valeur_mail", userRegistrationDto.getMail());
        model.addAttribute("valeur_telephone", userRegistrationDto.getTelephone());
        model.addAttribute("valeur_nom", userRegistrationDto.getNom());
        model.addAttribute("valeur_prenom", userRegistrationDto.getPrenom());
        model.addAttribute("valeur_pseudo", userRegistrationDto.getPseudo());
        model.addAttribute("valeur_rue", userRegistrationDto.getRue());
        model.addAttribute("valeur_code_postal", userRegistrationDto.getCodePostal());
        model.addAttribute("valeur_ville", userRegistrationDto.getVille());
        return Constante.PAGE_INSCRIPTION;
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
package fr.ecopoint.web.controleur;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.service.UserService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.UserModificationDto;
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
@RequestMapping("/mesinformations")
public class MesInformationControleur {

    private final static Logger logger = LogManager.getLogger(MesInformationControleur.class);

    private final UserService userService;

    public MesInformationControleur(final UserService userService){
        this.userService = userService;
    }

    @ModelAttribute("userModificationDto")
    public UserModificationDto userModificationDto() {
        return new UserModificationDto();
    }

    @GetMapping
    public String getMesinformations(final HttpSession session,final Model model) {
        User user = (User) session.getAttribute(Constante.USER_SESSION);
        if(user == null){
            return Constante.PAGE_REDIRECT_ACCEUIL;
        }else{
            model.addAttribute("valeur_mail", user.getMail());
            model.addAttribute("valeur_telephone", user.getTelephone());
            model.addAttribute("valeur_nom", user.getNom());
            model.addAttribute("valeur_prenom", user.getPrenom());
            model.addAttribute("valeur_adresse", user.getAdresse());
            model.addAttribute("valeur_ville", user.getAdresse().getVille());
            model.addAttribute("valeur_code_postale", user.getAdresse().getCodePostal());
            return Constante.PAGE_MES_INFORMATIONS;
        }
    }

    @PostMapping
    public String postMesinformations(final Model model, @ModelAttribute("userModificationDto") final UserModificationDto userModificationDto) {
        valideDonnee(model,userModificationDto);
        try {
            User user = (User) model.getAttribute("user");
            if (this.userService.exit(user) && this.userService.save(user)) {
                model.addAttribute(Constante.MODEL_MESSAGE,"Vos données ont bien été modifier.");
                return Constante.PAGE_ACCEUIL;
            } else {
                throw new UserException(MessageEx.MESSAGE_EXCEPTION_ERREUR_INTERNE);
            }
        } catch (final UserException userException) {
            model.addAttribute(Constante.ERREUR_INTERNE, userException.getMessage());
            logger.error("Erreur durant le traitement des données du userException :".concat(userException.getMessage()));
        } catch (final Exception e) {
            model.addAttribute(Constante.ERREUR_INTERNE, "Une erreur interne est survenu. Merci de bien vouloir recommencer nous en excuser");
            logger.error("Erreur autre :".concat(e.getMessage()));
        }

        model.addAttribute("valeur_mail", userModificationDto.getMail());
        model.addAttribute("valeur_telephone", userModificationDto.getTelephone());
        model.addAttribute("valeur_nom", userModificationDto.getNom());
        model.addAttribute("valeur_prenom", userModificationDto.getPrenom());
        model.addAttribute("valeur_adresse", userModificationDto.getAdresse());
        model.addAttribute("valeur_ville", userModificationDto.getVille());
        model.addAttribute("valeur_code_postale", userModificationDto.getCodePostal());

        return Constante.PAGE_MES_INFORMATIONS;
    }

    private void valideDonnee(final Model model, final UserModificationDto userModificationDto){
        if (userModificationDto.getNouveauMotDePasse() == null || userModificationDto.getNouveauMotDePasse().trim().isEmpty()) {
            model.addAttribute("erreurMotDePasse", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if (!userModificationDto.getNouveauMotDePasse().equals(userModificationDto.getConfirmationMotDePasse())) {
            model.addAttribute("erreurMotDePasse2", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        if (userModificationDto.getMail() == null || !userModificationDto.getMail().matches(UserConstante.REGEX_VALIDATION_MAL)) {
            model.addAttribute("erreurMail", MessageEx.MESSAGE_EXCEPTION_MAIL);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if (userModificationDto.getTelephone() == null || !userModificationDto.getTelephone().matches(UserConstante.REGEX_VALIDATION_TEL)) {
            model.addAttribute("erreurTelephone", MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
        }
        if (userModificationDto.getNom() == null || userModificationDto.getNom().trim().isEmpty()) {
            model.addAttribute("erreurNom", MessageEx.MESSAGE_EXCEPTION_NOM);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_NOM);
        }
        if (userModificationDto.getPrenom() == null || userModificationDto.getPrenom().trim().isEmpty()) {
            model.addAttribute("erreurPrenom", MessageEx.MESSAGE_EXCEPTION_PRENOM);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }
        if (userModificationDto.getAdresse() == null || userModificationDto.getAdresse().trim().isEmpty()) {
            model.addAttribute("erreurAdresse", MessageEx.MESSAGE_EXCEPTION_ADRESSE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_ADRESSE);
        }
    }

    @GetMapping("/ConfirmationSupression")
    private String suppressionCompte(final HttpSession session,final Model model){
        logger.debug("suppressionCompte");
        User user = (User) session.getAttribute(Constante.USER_SESSION);
        if(user != null){
            this.userService.delete(user);
            model.addAttribute(Constante.MODEL_MESSAGE,"Votre compte à bien été supprimer.");
            return Constante.PAGE_REDIRECT_SE_DECONNECTER;
        }else{
            return Constante.PAGE_REDIRECT_ACCEUIL;
        }
    }
}


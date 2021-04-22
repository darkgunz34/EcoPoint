package fr.ecopoint.web.controleur;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.factory.FactoryAdresse;
import fr.ecopoint.model.factory.FactoryUser;
import fr.ecopoint.service.UserService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.PasswordModificationDto;
import fr.ecopoint.web.dto.entities.UserModificationDto;
import fr.ecopoint.web.tools.Utils;
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

    @ModelAttribute("passwordModificationDto")
    public PasswordModificationDto passwordModificationDto() {
        return new PasswordModificationDto();
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
            model.addAttribute("valeur_rue",user.getAdresse().getRue());
            model.addAttribute("valeur_code_postale", user.getAdresse().getCodePostal());
            return Constante.PAGE_MES_INFORMATIONS;
        }
    }

    @PostMapping
    public String postMesinformations(final HttpSession session,final Model model, @ModelAttribute("userModificationDto") final UserModificationDto userModificationDto) {
        Utils.valideUserModificationDto(model, userModificationDto);
        try {
            User user = (User) session.getAttribute(Constante.USER_SESSION);
            FactoryUser.getUserFromUpdate(user,userModificationDto);
            Adresse adresse = FactoryAdresse.getAdresseFromUpdate(user,userModificationDto);
            user.setAdresse(adresse);
            if (this.userService.exit(user) && this.userService.save(user,false)) {
              session.setAttribute(Constante.MODEL_MESSAGE,"Vos données ont bien été modifier.");
              return Constante.PAGE_REDIRECT_ACCEUIL;
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
        model.addAttribute("valeur_ville", userModificationDto.getVille());
        model.addAttribute("valeur_rue",userModificationDto.getRue());
        model.addAttribute("valeur_code_postale", userModificationDto.getCodePostal());

        return Constante.PAGE_MES_INFORMATIONS;
    }

    @PostMapping("/passwordupdate")
    private String postUpdatePassword(final HttpSession session,final Model model,@ModelAttribute("passwordModificationDto") final PasswordModificationDto passwordModificationDto){
        logger.debug("postUpdatePassword");
        User user = (User) session.getAttribute(Constante.USER_SESSION);
        if (user != null && passwordModificationDto.getMotDePasse().equals(passwordModificationDto.getConfirmationMotDePasse())) {
            user.setPassword(passwordModificationDto.getMotDePasse());
            this.userService.save(user, true);
            session.setAttribute(Constante.MODEL_MESSAGE, "Votre mot de passe à bien été enregister.");
        }
        return Constante.PAGE_REDIRECT_ACCEUIL;
    }

    @GetMapping("/ConfirmationSupression")
    private String suppressionCompte(final HttpSession session,final Model model){
        logger.debug("suppressionCompte");
        User user = (User) session.getAttribute(Constante.USER_SESSION);
        if(user != null){
            this.userService.delete(user);
            session.setAttribute(Constante.MODEL_MESSAGE,"Votre compte à bien été supprimer.");
            return Constante.PAGE_REDIRECT_SE_DECONNECTER;
        }else{
            return Constante.PAGE_REDIRECT_ACCEUIL;
        }
    }
}


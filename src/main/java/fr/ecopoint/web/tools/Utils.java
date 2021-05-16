package fr.ecopoint.web.tools;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.web.dto.entities.UserModificationDto;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.springframework.ui.Model;

public final class Utils {
    private Utils(){

    }

    public static void valideUserModificationDto(final Model model, final UserModificationDto userModificationDto){
        valideUserParam(model, userModificationDto.getMail(), userModificationDto.getTelephone(), userModificationDto.getNom(), userModificationDto.getPrenom());
    }

    public static void valideUserRegistrationDto(final Model model, final UserRegistrationDto userRegistrationDto){
        if (userRegistrationDto.getMotDePasse() == null || userRegistrationDto.getMotDePasse().trim().isEmpty()) {
            model.addAttribute("erreurMotDePasse", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if (!userRegistrationDto.getMotDePasse().equals(userRegistrationDto.getMotDePasse2())) {
            model.addAttribute("erreurMotDePasse2", MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        valideUserParam(model, userRegistrationDto.getMail(), userRegistrationDto.getTelephone(), userRegistrationDto.getNom(), userRegistrationDto.getPrenom());
    }

    public static void valideUserParam(Model model, String mail, String telephone, String nom, String prenom) {
        if (mail == null || !mail.matches(UserConstante.REGEX_VALIDATION_MAL)) {
            model.addAttribute("erreurMail", MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if (telephone == null || !telephone.matches(UserConstante.REGEX_VALIDATION_TEL)) {
            model.addAttribute("erreurTelephone", MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
        }
        if (nom == null || nom.trim().isEmpty()) {
            model.addAttribute("erreurNom", MessageEx.MESSAGE_EXCEPTION_NOM);
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            model.addAttribute("erreurPrenom", MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }
    }
}

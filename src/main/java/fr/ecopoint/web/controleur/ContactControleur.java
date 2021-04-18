package fr.ecopoint.web.controleur;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Contact;
import fr.ecopoint.model.exception.ContactException;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.factory.FactoryContact;
import fr.ecopoint.model.service.ContactService;
import fr.ecopoint.web.Constante.Constante;
import fr.ecopoint.web.dto.entities.ContactDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactControleur {

    private static final Logger logger = LogManager.getLogger(ContactControleur.class);
    private final ContactService contactService;

    public ContactControleur(final ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute("contactDto")
    public ContactDto contactDto() {
        return new ContactDto();
    }

    @GetMapping
    public String getContact() {
        logger.debug("getContact()");
        return Constante.PAGE_CONTACT;
    }

    @PostMapping
    public String postContact(final Model model, @ModelAttribute("contactDto") final ContactDto contactDto) {
        logger.debug("postContact()");
        boolean valide = true;
        if (contactDto.getNom() == null || contactDto.getNom().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurNom", MessageEx.MESSAGE_EXCEPTION_NOM);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_NOM);
        }
        if (contactDto.getMail() == null || !contactDto.getMail().matches(UserConstante.REGEX_VALIDATION_MAL)) {
            valide = false;
            model.addAttribute("erreurMail", MessageEx.MESSAGE_EXCEPTION_MAIL);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if (contactDto.getObjet() == null || contactDto.getObjet().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurObjet", MessageEx.MESSAGE_EXCEPTION_OBJET);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_OBJET);
        }
        if (contactDto.getMessage() == null || contactDto.getMessage().trim().isEmpty()) {
            valide = false;
            model.addAttribute("erreurMessage", MessageEx.MESSAGE_EXCEPTION_MESSAGE);
            logger.debug(MessageEx.MESSAGE_EXCEPTION_MESSAGE);
        }

        if (valide) {
            try {
                final Contact contact = FactoryContact.getContactAvecParam(contactDto);
                if (this.contactService.save(contact)) {
                    model.addAttribute(Constante.MODEL_MESSAGE, "Votre demande a bien été prise en compte. Un administrateur se charge de");
                    return Constante.PAGE_ACCEUIL;
                } else {
                    throw new ContactException(MessageEx.MESSAGE_EXCEPTION_ERREUR_INTERNE);
                }
            } catch (final ContactException contactException) {
                model.addAttribute(Constante.ERREUR_INTERNE, contactException.getMessage());
                logger.error("Erreur durant le traitement des données du contact :".concat(contactException.getMessage()));
            }
        }
        model.addAttribute("valeur_mail", contactDto.getMail());
        model.addAttribute("valeur_objet", contactDto.getObjet());
        model.addAttribute("valeur_nom", contactDto.getNom());
        model.addAttribute("valeur_message", contactDto.getMessage());
        return Constante.PAGE_CONTACT;
    }
}
package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Contact;
import fr.ecopoint.model.exception.ContactException;
import fr.ecopoint.model.exception.MessageException;
import fr.ecopoint.web.dto.entities.ContactDto;

/**
 * Ensemble des méthodes pour instancier un contact
 */
public final class FactoryContact {

    /**
     * Constructeur privé.
     */
    private FactoryContact(){
    }

    /**
     * Méthode pour retourner un contact avec les paramètres saisie par l'utilisateur.
     * @return L'objet de contact.
     */
    public static Contact getContactAvecParam(final ContactDto contactDto) throws ContactException{
        if(nonValideMail(contactDto.getMail())){
            throw new ContactException(MessageException.MESSAGE_EXCEPTION_MAIL);
        }
        if(!champNonVide(contactDto.getNom())){
            throw new ContactException(MessageException.MESSAGE_EXCEPTION_NOM);
        }
        if(!champNonVide(contactDto.getObjet())){
            throw new ContactException(MessageException.MESSAGE_EXCEPTION_OBJET);
        }
        if(!champNonVide(contactDto.getMessage())){
            throw new ContactException(MessageException.MESSAGE_EXCEPTION_MESSAGE);
        }
        return new Contact( contactDto.getMail(), contactDto.getNom(),contactDto.getObjet(),contactDto.getMessage());
    }

    /**
     * Méthode pour vérifier si le mail est valide.
     * @param mail Le mail à contrôler.
     * @return True s'il est valide.
     */
    private static boolean nonValideMail(final String mail){
        return !champNonVide(mail) || !mail.matches(UserConstante.REGEX_VALIDATION_MAL);
    }

    /**
     * Méthode pour vérifier si un champ est vide ou null.
     * @param chaine La chaine à contrôler
     * @return True si elle contient au moins un caractère.
     */
    private static boolean champNonVide(final String chaine){
        return chaine != null && !chaine.trim().isEmpty();
    }

}

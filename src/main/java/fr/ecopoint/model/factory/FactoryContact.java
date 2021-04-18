package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Contact;
import fr.ecopoint.model.exception.ContactException;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.web.dto.entities.ContactDto;

public final class FactoryContact {

    private FactoryContact(){
    }

    public static Contact getContactAvecParam(final ContactDto contactDto) throws ContactException{
        if(nonValideMail(contactDto.getMail())){
            throw new ContactException(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if(champVide(contactDto.getNom())){
            throw new ContactException(MessageEx.MESSAGE_EXCEPTION_NOM);
        }
        if(champVide(contactDto.getObjet())){
            throw new ContactException(MessageEx.MESSAGE_EXCEPTION_OBJET);
        }
        if(champVide(contactDto.getMessage())){
            throw new ContactException(MessageEx.MESSAGE_EXCEPTION_MESSAGE);
        }
        return new Contact( contactDto.getMail(), contactDto.getNom(),contactDto.getObjet(),contactDto.getMessage());
    }

    private static boolean nonValideMail(final String mail){
        return champVide(mail) || !mail.matches(UserConstante.REGEX_VALIDATION_MAL);
    }

    private static boolean champVide(final String chaine){
        return chaine == null || chaine.trim().isEmpty();
    }

}

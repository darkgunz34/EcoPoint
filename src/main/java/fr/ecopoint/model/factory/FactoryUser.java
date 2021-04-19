package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.MessageEx;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserLoginDto;
import fr.ecopoint.web.dto.entities.UserModificationDto;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FactoryUser {

    private FactoryUser(){
    }

    public static User getUserFromCreation(final UserRegistrationDto userRegistrationDto, final Role role) throws UserException, RoleException {
        if(valideNonNullMotDePasse(userRegistrationDto.getMotDePasse(), userRegistrationDto.getMotDePasse2())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if(valideNonIdentiqueMotDePasse(userRegistrationDto.getMotDePasse(), userRegistrationDto.getMotDePasse2())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        if(valideNonMail(userRegistrationDto.getMail())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if(valideNonTelephone(userRegistrationDto.getTelephone())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
        }

        if(champNonVide(userRegistrationDto.getNom())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_NOM);
        }

        if(champNonVide(userRegistrationDto.getPrenom())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }

        if(nonValideRole(role)){
            throw new RoleException(MessageEx.MESSAGE_EXCEPTION_VIDE);
        }

        return new User(userRegistrationDto.getMail(),userRegistrationDto.getMotDePasse(),userRegistrationDto.getNom(),
                        userRegistrationDto.getPrenom(),userRegistrationDto.getTelephone(),role);
    }

    public static User getUserFromLogin(final UserLoginDto userLoginDto, final Role role) throws UserException,RoleException{
        if(valideNonMail(userLoginDto.getMail())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_IDENTIFICANT_CONNEXION);
        }

        if(champNonVide(userLoginDto.getMotDePasse())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_IDENTIFICANT_CONNEXION);
        }

        if(nonValideRole(role)){
            throw new RoleException(MessageEx.MESSAGE_EXCEPTION_VIDE);
        }

        return new User(userLoginDto.getMail(), userLoginDto.getMotDePasse(), role);
    }

    private static boolean champNonVide(final String chaine){
        return !(chaine != null && !chaine.trim().isEmpty());
    }

    private static boolean nonValideRole(final Role role){
        return role == null || role.getName() == null;
    }

    private static boolean valideNonNullMotDePasse(final String motDePasse,final String motDePasse2){
        return champNonVide(motDePasse) && champNonVide(motDePasse2);
    }

    private static boolean valideNonIdentiqueMotDePasse(final String motDePasse,final String motDePasse2){
        return !motDePasse.equals(motDePasse2);
    }

    private static boolean valideNonTelephone(final String telephone){
        return !(!champNonVide(telephone) && telephone.matches(UserConstante.REGEX_VALIDATION_TEL));
    }

    private static boolean valideNonMail(final String mail){
        return !(!champNonVide(mail) && mail.matches(UserConstante.REGEX_VALIDATION_MAL));
    }

    public static void getUserFromUpdate(User user, UserModificationDto userModificationDto) throws UserException{
        if(valideNonMail(userModificationDto.getMail())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_MAIL);
        }
        if(valideNonTelephone(userModificationDto.getTelephone())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_TELEPHONE);
        }

        if(champNonVide(userModificationDto.getNom())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_NOM);
        }

        if(champNonVide(userModificationDto.getPrenom())){
            throw new UserException(MessageEx.MESSAGE_EXCEPTION_PRENOM);
        }

        user.setMail(userModificationDto.getMail());
        user.setTelephone(userModificationDto.getTelephone());
        user.setNom(userModificationDto.getNom());
        user.setPrenom(userModificationDto.getPrenom());
    }
}

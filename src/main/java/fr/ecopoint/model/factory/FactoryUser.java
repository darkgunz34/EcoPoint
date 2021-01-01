package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.MessageException;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserRegistrationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ensemble des méthodes pour instancier un user
 */
public final class FactoryUser {

    /**
     * Le logger de la class.
     */
    private final static Logger logger = LogManager.getLogger(FactoryUser.class);

    /**
     * Constructeur privé.
     */
    private FactoryUser(){
    }

    /**
     * Méthode pour créer un user depuis une nouvelle création qui n'existe pas encore en BDD.
     * @param userRegistrationDto L'ensemble des paramètres pour créer le {@link User}
     * @return Une nouvelle instance avec l'ensemble des données fournis.
     * @throws UserException lève une exception en cas de données en entrer incohérente.
     */
    public static User getUserFromCreation(final UserRegistrationDto userRegistrationDto, final Role role) throws UserException, RoleException {
        logger.debug("Création de user avec les paramètres suivants : ".concat(userRegistrationDto.toString()).concat(" et ").concat(role.toString()));
        if(userRegistrationDto.getMotDePasse() == null || userRegistrationDto.getMotDePasse().trim().isEmpty()){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if(!userRegistrationDto.getMotDePasse().equals(userRegistrationDto.getMotDePasse2())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        if(userRegistrationDto.getMail() == null || !userRegistrationDto.getMail().matches(UserConstante.REGEX_VALIDATION_MAL)){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MAIL);
        }
        if(userRegistrationDto.getTelephone() ==null || !userRegistrationDto.getTelephone().matches(UserConstante.REGEX_VALIDATION_TEL)){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_TELEPHONE);
        }
        if(userRegistrationDto.getNom() == null || userRegistrationDto.getNom().trim().isEmpty()){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_NOM);
        }
        if(userRegistrationDto.getPrenom() == null || userRegistrationDto.getPrenom().trim().isEmpty()){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_PRENOM);
        }
        if(userRegistrationDto.getAdresse() == null || userRegistrationDto.getAdresse().trim().isEmpty()){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_ADRESSE);
        }
        if(role.getName() == null){
            throw new RoleException(MessageException.MESSAGE_EXCEPTION_VIDE);
        }
        return new User(userRegistrationDto.getMail(),userRegistrationDto.getMotDePasse(),userRegistrationDto.getNom(),
                        userRegistrationDto.getPrenom(),userRegistrationDto.getTelephone(),
                        userRegistrationDto.getAdresse(),role);
    }
}

package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.UserConstante;
import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.MessageException;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.web.dto.entities.UserLoginDto;
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
     * @throws RoleException lève une exception en cas de données non instancié / vide.
     */
    public static User getUserFromCreation(final UserRegistrationDto userRegistrationDto, final Role role) throws UserException, RoleException {
        logger.debug("Création de user avec les paramètres suivants : ".concat(userRegistrationDto.toString()).concat(" et ").concat(role.toString()));
        if(!valideNonNullMotDePasse(userRegistrationDto.getMotDePasse(), userRegistrationDto.getMotDePasse2())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE);
        }
        if(!valideIdentiqueMotDePasse(userRegistrationDto.getMotDePasse(), userRegistrationDto.getMotDePasse2())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MOT_DE_PASSE);
        }
        if(nonValideMail(userRegistrationDto.getMail())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_MAIL);
        }
        if(!valideTelephone(userRegistrationDto.getTelephone())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_TELEPHONE);
        }

        if(nonValideAdresse(userRegistrationDto.getNom())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_NOM);
        }

        if(nonValideAdresse(userRegistrationDto.getPrenom())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_PRENOM);
        }

        if(nonValideAdresse(userRegistrationDto.getAdresse())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_ADRESSE);
        }

        if(nonValideRole(role)){
            throw new RoleException(MessageException.MESSAGE_EXCEPTION_VIDE);
        }

        return new User(userRegistrationDto.getMail(),userRegistrationDto.getMotDePasse(),userRegistrationDto.getNom(),
                        userRegistrationDto.getPrenom(),userRegistrationDto.getTelephone(),
                        userRegistrationDto.getAdresse(),role);
    }

    /**
     * Méthode pour créer un user depuis une nouvelle création qui existe potentiellement en BDD.
     * @param userLoginDto L'ensemble des paramètres pour charger le {@link User}.
     * @return Une nouvelle instance avec l'ensemble des données fournis.
     * @throws UserException lève une exception en cas de données en entrer incohérente.
     * @throws RoleException lève une exception en cas de données non instancié / vide.
     */
    public static User getUserFromLogin(final UserLoginDto userLoginDto, final Role role) throws UserException,RoleException{
        if(nonValideMail(userLoginDto.getMail())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_IDENTIFICANT_CONNEXION);
        }

        if(!champNonVide(userLoginDto.getMotDePasse())){
            throw new UserException(MessageException.MESSAGE_EXCEPTION_IDENTIFICANT_CONNEXION);
        }

        if(nonValideRole(role)){
            throw new RoleException(MessageException.MESSAGE_EXCEPTION_VIDE);
        }

        return new User(userLoginDto.getMail(), userLoginDto.getMotDePasse(), role);
    }

    /**
     * Méthode pour vérifier si un champ est vide ou null.
     * @param chaine La chaine à contrôler
     * @return True si elle contient au moins un caractère.
     */
    private static boolean champNonVide(final String chaine){
        return chaine != null && !chaine.trim().isEmpty();
    }

    /**
     * Méthode pour vérifier si le role est instancier ou non.
     * @param role Le role à contrôler
     * @return True s'il est valide.
     */
    private static boolean nonValideRole(final Role role){
        return role == null || role.getName() == null;
    }

    /**
     * Méthode pour vérifier si les mots de passes sont non vide.
     * @param motDePasse Le premier mot de passe à comparer.
     * @param motDePasse2 Le second mot de passe à comparer
     * @return True s'ils sont non vide.
     */
    private static boolean valideNonNullMotDePasse(final String motDePasse,final String motDePasse2){
        return champNonVide(motDePasse) && champNonVide(motDePasse2);
    }

    /**
     * Méthode pour vérifier si les mots de passes sont valide.
     * @param motDePasse Le premier mot de passe à comparer.
     * @param motDePasse2 Le second mot de passe à comparer
     * @return True s'il est valide.
     */
    private static boolean valideIdentiqueMotDePasse(final String motDePasse,final String motDePasse2){
        return motDePasse.equals(motDePasse2);
    }

    /**
     * Méthode pour vérifier si le téléphone est valide.
     * @param telephone Le téléphone à contrôler.
     * @return True s'il est valide.
     */
    private static boolean valideTelephone(final String telephone){
        return champNonVide(telephone) && telephone.matches(UserConstante.REGEX_VALIDATION_TEL);
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
     * Méthode pour vérifier si le nom est valide.
     * @param nom Le nom à contrôler.
     * @return True si elle est valide.
     */
    private static boolean valideNom(final String nom){
        return nom != null && nom.trim().isEmpty();
    }

    /**
     * Méthode pour vérifier si le prénom est valide.
     * @param prenom Le prénom à contrôler.
     * @return True si elle est valide.
     */
    private static boolean validePrenom(final String prenom){
        return champNonVide(prenom);
    }

        /**
         * Méthode pour vérifier si l'adresse est valide.
         * @param adresse L'adresse à contrôler.
         * @return True si elle est valide.
         */
    private static boolean nonValideAdresse(final String adresse){
        return !champNonVide(adresse);
    }
}

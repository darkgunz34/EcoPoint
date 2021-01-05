package fr.ecopoint.model.exception;

/**
 * Ensemble des Exceptions pour les utilisateurs.
 */
public class UserException extends Exception{

    /**
     * Constructeur de l'exception.
     * @param message Le message de l'erreur.
     */
    public UserException(final String message){
        super(message);
    }
}

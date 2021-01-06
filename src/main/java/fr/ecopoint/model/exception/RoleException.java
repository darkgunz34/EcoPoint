package fr.ecopoint.model.exception;

/**
 * Ensemble des Exceptions pour les roles utilisateurs.
 */
public class RoleException extends Exception{

    /**
     * Constructeur de l'exception.
     * @param message Le message de l'erreur.
     */
    public RoleException(final String message){
        super(message);
    }
}

package fr.ecopoint.model.exception;

/**
 * Ensemble des Exceptions pour les envoie de mail pour les utilisateurs.
 */
public class MailException extends Exception{

    /**
     * Constructeur de l'exception.
     * @param message Le message de l'erreur.
     */
    public MailException(final String message){
        super(message);
    }
}

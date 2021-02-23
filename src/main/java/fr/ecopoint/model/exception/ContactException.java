package fr.ecopoint.model.exception;

/**
 * Ensemble des Exceptions pour les contacts de support demandé par les utilisateurs.
 */
public class ContactException extends Exception{

    /**
     * Constructeur de l'exception.
     * @param message Le message de l'erreur.
     */
    public ContactException(final String message){
        super(message);
    }
}

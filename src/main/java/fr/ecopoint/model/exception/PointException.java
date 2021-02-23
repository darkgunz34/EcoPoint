package fr.ecopoint.model.exception;

/**
 * Ensemble des Exceptions pour les Points.
 */
public class PointException extends Exception{

    /**
     * Constructeur de l'exception.
     * @param message Le message de l'erreur.
     */
    public PointException(final String message){
        super(message);
    }
}

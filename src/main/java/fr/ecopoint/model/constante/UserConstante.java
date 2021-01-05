package fr.ecopoint.model.constante;

/**
 * Ensemble des constances liées au User.
 */
public class UserConstante {

    /**
     * Constructeur privé.
     */
    private UserConstante(){

    }

    /**
     * Regex de contrôle pour la validation d'adresse mail.
     */
    public static final String REGEX_VALIDATION_MAL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    /**
     * Regex de contrôle pour la validation de téléphone en france uniquement sur 10 chiffres.
     */
    public static final String REGEX_VALIDATION_TEL = "^\\d{10}$";

}

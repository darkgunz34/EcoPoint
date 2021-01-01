package fr.ecopoint.model.exception;

/**
 * Ensemble des messages concernant les exceptions des entités en BDD.
 */
public final class MessageException {

    /**
     * Constructeur privé.
     */
    private MessageException() {

    }

    /**
     * La données saisie est vide.
     */
    public static final String MESSAGE_EXCEPTION_VIDE = "La donnée saisie est vide";

    /**
     * Message pour une lever d'exception quand l"adresse est déjà utiliser pour un autre compte.
     */
    public static final String MESSAGE_EXCEPTION_DOUBLON ="L'adresse mail est déjà utiliser. Vous ne pouvez pas créer de nouveau compte";

    /**
     * Message pour une lever d'exception quand le mot de passe n'est pas identique.
     */
    public static final String MESSAGE_EXCEPTION_MOT_DE_PASSE ="Les mots de passe doivent être identiques";

    /**
     * Message pour une lever d'exception quand le mot de passe est pas vide.
     */
    public static final String MESSAGE_EXCEPTION_MOT_DE_PASSE_VIDE ="Le mode de passe ne doit pas être vide";

    /**
     * Message pour une lever d'exception quand l'adresse mail est pas valide.
     */
    public static final String MESSAGE_EXCEPTION_MAIL = "L'adresse Mail n'est pas valide";

    /**
     * Message pour une lever d'exception quand le téléphone est pas valide.
     */
    public static final String MESSAGE_EXCEPTION_TELEPHONE = "Le téléphone n'est pas valide";

    /**
     * Message pour une lever d'exception quand le nom est non renseigné.
     */
    public static final String MESSAGE_EXCEPTION_NOM = "Le nom ne peut pas être vide";

    /**
     * Message pour une lever d'exception quand le prénom est non renseigné.
     */
    public static final String MESSAGE_EXCEPTION_PRENOM = "Le prénom ne peut pas être vide";

    /**
     * Message pour une lever d'exception quand l'adresse est non renseigné.
     */
    public static final String MESSAGE_EXCEPTION_ADRESSE = "L'adresse ne peut pas être vide";
}

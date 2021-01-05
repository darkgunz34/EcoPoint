package fr.ecopoint.model.constante;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Ensemble des constances liées au Role.
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
public final class RoleConstante {

    /**
     * Constructeur privé.
     */
    private RoleConstante(){

    }

    /**
     * Le rôle par défaut lors de la création d'un compte.
     */
    public static final String USER_ROLE_PAR_DEFAULT = "UTILISATEUR";
}

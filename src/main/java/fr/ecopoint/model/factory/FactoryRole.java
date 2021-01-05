package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.RoleConstante;
import fr.ecopoint.model.entities.Role;

/**
 * Ensemble des méthodes pour instancier un role
 */
public final class FactoryRole {
    /**
     * Constructeur privé.
     */
    private FactoryRole(){
    }

    /**
     * Méthode pour retourner le rôle avec les valeurs par défaut.
     * @return Le rôle par défaut.
     */
    public static Role getRoleParDefault(){
        return new Role(RoleConstante.USER_ROLE_PAR_DEFAULT);
    }

}

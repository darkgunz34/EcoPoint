package fr.ecopoint.model.factory;

import fr.ecopoint.model.constante.RoleConstante;
import fr.ecopoint.model.entities.Role;

public final class FactoryRole {

    private FactoryRole(){
    }

    public static Role getRoleParDefault(){
        return new Role(RoleConstante.USER_ROLE_PAR_DEFAULT);
    }

}

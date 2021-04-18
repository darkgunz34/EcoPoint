package fr.ecopoint.model.constante;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
public final class RoleConstante {

    private RoleConstante(){

    }

    public static final String USER_ROLE_PAR_DEFAULT = "UTILISATEUR";
}

package fr.ecopoint.service;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.RoleException;

public interface RoleService{

    boolean save(Role role);

    boolean exit(Role role);

    Role findByName(String name) throws RoleException;
}
package fr.ecopoint.service;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.RoleException;

/**
 * Ensemble des méthodes nécessaire pour le CRUD du role.
 */
public interface RoleService{

    /**
     * Méthode pour enregistrer un user en BDD.
     * @param role Le user a sauvegarder.
     * @return True s'il est bien sauvegarder.
     */
    boolean save(Role role);

    /**
     * Méthode pour vérifier si le role existe en BDD. Le contrôle repose sur le nom.
     * @param role Le user a vérifier.
     * @return True s'il existe.
     */
    Boolean exit(Role role);

    /**
     * Méthode pour retourner le role premier
     * @param name le nom du role à rechercher
     * @throws RoleException Si le role n'existe pas en BDD.
     */
    Role findByName(String name) throws RoleException;
}
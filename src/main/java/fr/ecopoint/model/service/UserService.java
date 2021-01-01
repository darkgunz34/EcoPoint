package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;

/**
 * Ensemble des méthodes nécessaire pour le CRUD du user.
 */
public interface UserService{

    /**
     * Méthode pour rechercher un user depuis son login
     * @param mail L'identifiant du user.
     * @return Retourne le user associé.
     * @throws UserException Exception si le user n'existe pas en BDD.
     */
    User findByMail(final String mail) throws UserException;

    /**
     * Méthode pour enregistrer un user en BDD.
     * @param user Le user a sauvegarder.
     * @return True s'il est bien ajouter.
     */
    boolean save(final User user);

    /**
     * Méthode pour vérifier si le user existe en BDD. Le contrôle repose sur le login.
     * @param user Le user a vérifier.
     * @return True s'il existe.
     */
    boolean exit(final User user);
}
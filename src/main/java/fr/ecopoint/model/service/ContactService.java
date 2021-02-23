package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.Contact;

/**
 * Ensemble des méthodes nécessaire pour le CRUD du contact.
 */
public interface ContactService {

    /**
     * Méthode pour enregistrer un contact en BDD.
     * @param contact Le contact a sauvegarder.
     * @return True s'il est bien ajouter.
     */
    boolean save(final Contact contact);

    /**
     * Méthode pour vérifier si le contact existe en BDD. Le contrôle repose sur l'id.
     * @param contact Le contact a vérifier.
     * @return True s'il existe.
     */
    boolean exit(final Contact contact);
}
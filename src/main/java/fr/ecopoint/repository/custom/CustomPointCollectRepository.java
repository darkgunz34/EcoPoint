package fr.ecopoint.repository.custom;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;

import java.util.List;

/**
 * CRUD Customisé pour la lecture de la table des points associés à un utilisateur.
 */
public interface CustomPointCollectRepository {

    /**
     * Méthode pour lire l'ensemble des points collecter pour un utilisateur depuis la BDD.
     * @param u Le user en question.
     * @return La liste des points qui lui sont associés.
     */
    List<PointCollect> readAllFromUser(User u);
}

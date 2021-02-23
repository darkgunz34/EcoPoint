package fr.ecopoint.service;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.PointException;

import java.util.List;

/**
 * Ensemble des méthodes nécessaire pour le CRUD du nombre de point.
 */
public interface PointCollectService {

    /**
     * Méthode pour récupérer La liste de point depuis le user.
     * @param user Le user en question
     * @return Les point stocker en BDD.
     * @throws PointException Exception s'il n'y a pas d'enregistrement associé en Table.
     */
    List<PointCollect> getPointCollectFromUser(User user) throws PointException;
}

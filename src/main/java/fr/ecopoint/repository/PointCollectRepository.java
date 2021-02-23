package fr.ecopoint.repository;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import java.util.List;

/**
 * Interface reliant la BDD et l'entité PointCollect.
 */
public interface PointCollectRepository{

    /**
     * Méthode pour rechercher une liste de point depuis l'utilisateur.
     *
     * @param user L'identifiant de l'utilisateur associé au point du point.
     * @return Le point s'il existe en BDD.
     */
    List<PointCollect> readAllFromUser(User user);

}

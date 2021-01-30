package fr.ecopoint.repository.custom;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * CRUD Customisé implémenter.
 */
@Repository
@Transactional(readOnly = true)
public class CustomPointCollectRepositoryImpl implements CustomPointCollectRepository{

    /**
     * Persistance Context de la BDD.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PointCollect> readAllFromUser(final User u){
        final String prepareReq = "SELECT a FROM User a LEFT JOIN FETCH a.lstPointsCollect WHERE a.id = :cle";
        final TypedQuery<User> req = this.em.createQuery(prepareReq, User.class);
        req.setParameter("cle",u.getId());
        return req.getSingleResult().getLstPointsCollect();
    }
}

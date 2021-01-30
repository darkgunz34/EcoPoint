package fr.ecopoint.service;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.PointException;
import fr.ecopoint.repository.custom.CustomPointCollectRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service en charge d'implémenter le CRUD Role.
 */
@Service
public class PointCollectServiceImpl implements PointCollectService{
    /**
     * Le logger de la class.
     */
    private static final Logger LOGGER = LogManager.getLogger(PointCollectServiceImpl.class);

    /**
     * Interface pour le CRUD spécifique.
     */
    private final CustomPointCollectRepository customPointCollectRepository;

    public PointCollectServiceImpl(final CustomPointCollectRepository customPointCollectRepository) {
        this.customPointCollectRepository = customPointCollectRepository;
    }

    @Override
    public List<PointCollect> getPointCollectFromUser(final User user) throws PointException {
        LOGGER.debug("getPointCollectFromUser()");
        final List<PointCollect> lstPointCollects = this.customPointCollectRepository.readAllFromUser(user);
        if(lstPointCollects == null || lstPointCollects.isEmpty()){
            throw new PointException("La liste des points associé à l'utilisateur est vide");
        }
        return lstPointCollects;
    }
}

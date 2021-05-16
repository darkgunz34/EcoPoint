package fr.ecopoint.service;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.PointException;

import java.util.List;

public interface PointCollectService {

    List<PointCollect> getPointCollectFromUser(User user) throws PointException;
}

package fr.ecopoint.repository;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;
import java.util.List;


public interface PointCollectRepository{

    List<PointCollect> readAllFromUser(User user);

}

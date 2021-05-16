package fr.ecopoint.repository.custom;

import fr.ecopoint.model.entities.PointCollect;
import fr.ecopoint.model.entities.User;

import java.util.List;

public interface CustomPointCollectRepository {

    List<PointCollect> readAllFromUser(User u);
}

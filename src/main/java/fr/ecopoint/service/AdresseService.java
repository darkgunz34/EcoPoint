package fr.ecopoint.service;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;

public interface AdresseService {

    boolean save(final Adresse adresse);
    boolean exit(final Adresse adresse);
}
package fr.ecopoint.service;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;

public interface UserService{

    User findByMail(final String mail) throws UserException;

    User findByMailAndPassword(final User user) throws UserException;

    boolean save(final User user,boolean updatePassword);

    boolean exit(final User user);

    boolean delete(final User user);
}
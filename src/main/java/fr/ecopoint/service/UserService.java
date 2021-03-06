package fr.ecopoint.service;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;

public interface UserService{

    User findByMail(final String mail) throws UserException;

    User findByMailAndPassword(final String mail,final String password) throws UserException;

    User findByPseudoAndPassword(final String pseudo,final String password) throws UserException;

    User readFromKey(long id);

    boolean save(final User user,boolean updatePassword);

    boolean exit(final User user);

    boolean delete(final User user);

    boolean exist(long key);
}
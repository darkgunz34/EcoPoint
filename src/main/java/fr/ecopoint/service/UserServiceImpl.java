package fr.ecopoint.service;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final RoleService roleService, final BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByMail(final String mail) throws UserException {
        logger.debug("Recherche un user avec le mail : {}",mail);
        final User user = this.userRepository.findByMail(mail);
        if(user != null){
            logger.debug("Recherche un user avec le mail avec succès");
            return user;
        }
        else{
            logger.debug("Le mail n'existe pas");
            throw new UserException("Le user avec le mail : ".concat(mail).concat(" n'existe pas en BDD."));
        }
    }

    @Override
    public User findByPseudoAndPassword(final String mail,final String password) throws UserException {
        User userFromDataBase = this.userRepository.findByPseudo(mail);
        if (this.passwordEncoder.matches(password, userFromDataBase.getPassword())) {
            return userFromDataBase;
        } else {
            throw new UserException("Erreur, le compte n'existe pas ou le mot de passe n'est pas valide");
        }
    }
    @Override
    public User findByMailAndPassword(final String mail,final String password) throws UserException {
        User userFromDataBase = this.userRepository.findByMail(mail);
        if (this.passwordEncoder.matches(password, userFromDataBase.getPassword())) {
            return userFromDataBase;
        } else {
            throw new UserException("Erreur, le compte n'existe pas ou le mot de passe n'est pas valide");
        }
    }

    @Override
    public boolean save(final User user,boolean updatePassword) {
        if(Boolean.FALSE.equals(this.roleService.exit(user.getRole()))){
            if(this.roleService.save(user.getRole())){
                logger.debug("Sauvegarde du role avec succès");
            }else{
                logger.error("Ajout du role impossible : {}",user.getRole());
            }
        }
        if(updatePassword){
            this.encodeMotDePasseUser(user);
        }
        this.userRepository.save(user);
        logger.debug("Sauvegarde du user avec succès");
        return this.exit(user);
    }

    @Override
    public boolean exit(final User user){
        return this.userRepository.findByMail(user.getMail()) != null;
    }

    @Override
    public boolean delete(final User user) {
        this.userRepository.delete(user);
        return !this.exit(user);
    }

    private void encodeMotDePasseUser(final User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }
}
package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.User;
import fr.ecopoint.model.exception.UserException;
import fr.ecopoint.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service en charge d'implémenter le CRUD User.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Le logger de la class.
     */
    private final static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Interface pour le CRUD User.
     */
    private final UserRepository userRepository;

    /**
     * Interface pour le CRUD Role
     */
    private final RoleService roleService;

    /**
     * Class pour asher le mot de passe.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructeur du service
     * @param userRepository Interface pour le CRUD User
     * @param roleService  Interface pour le CRUD Role
     * @param passwordEncoder Class pour asher le mot de passe.
     */
    public UserServiceImpl(final UserRepository userRepository, final RoleService roleService, final BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByMail(final String mail) throws UserException {
        logger.debug("Recherche un user avec le mail : ".concat(mail));
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
    public boolean save(final User user) {
        if(!this.roleService.exit(user.getRole())){
            this.roleService.save(user.getRole());
            logger.debug("Sauvegarde du role avec succès");
        }
        this.encodeMotDePasse(user);
        this.userRepository.save(user);
        logger.debug("Sauvegarde du user avec succès");
        return this.exit(user);
    }

    @Override
    public boolean exit(final User user){
        return this.userRepository.findByMail(user.getMail()) != null;
    }

    /**
     * Encode le mode de passe avant le traitement en BDD.
     * @param user Le user.
     */
    private void encodeMotDePasse(final User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }
}
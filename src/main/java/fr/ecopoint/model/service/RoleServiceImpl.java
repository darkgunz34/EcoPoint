package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.model.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * Service en charge d'implémenter le CRUD Role.
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * Le logger de la class.
     */
    private final static Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    /**
     * Interface pour le CRUD Role.
     */
    private final RoleRepository roleRepository;

    /**
     * Constructeur du service.
     * @param roleRepository  Interface pour le CRUD Role
     */
    public RoleServiceImpl(final RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(final String name) throws RoleException {
        logger.debug("Recherche un role avec le nom : ".concat(name));
        final Role role = this.roleRepository.findByName(name);
        if(role != null){
            logger.debug("Recherche un role avec le nom avec succès");
            return role;
        }else{
            logger.debug("Le role n'existe pas");
            throw new RoleException("Le role avec le nom : ".concat(name).concat(" n'existe pas en BDD."));
        }
    }

    @Override
    public boolean save(final Role role) {
        this.roleRepository.save(role);
        logger.debug("Sauvegarde du role avec succès");
        return this.exit(role);
    }

    @Override
    public Boolean exit(final Role role){
        return this.roleRepository.findByName(role.getName()) != null;
    }
}
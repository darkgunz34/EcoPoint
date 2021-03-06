package fr.ecopoint.service;

import fr.ecopoint.model.entities.Role;
import fr.ecopoint.model.exception.RoleException;
import fr.ecopoint.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(final String name) throws RoleException {
        logger.debug("Recherche un role avec le nom : {}", name);
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
    public boolean exit(final Role role){
        return this.roleRepository.findByName(role.getName()) != null;
    }
}
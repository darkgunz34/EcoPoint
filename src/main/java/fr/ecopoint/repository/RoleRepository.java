package fr.ecopoint.repository;

import fr.ecopoint.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface reliant la BDD et l'entité Role.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {

    /**
     * Méthode pour rechercher un user depuis son role.
     *
     * @param role Le nom du role.
     * @return Le role s'il existe en BDD.
     */
    Role findByName(String role);
}
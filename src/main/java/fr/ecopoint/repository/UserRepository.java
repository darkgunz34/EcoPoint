package fr.ecopoint.repository;

import fr.ecopoint.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface reliant la BDD et l'entité User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    /**
     * Méthode pour rechercher un user depuis son adresse mail.
     *
     * @param mail L'adresse mail associé.
     * @return Le user s'il existe en BDD.
     */
    User findByMail(String mail);

    /**
     * Méthode pour rechercher un user depuis son adresse mail.
     *
     * @param mail L'adresse mail associé.
     * @param password Le mot de passe hash associé.
     * @return Le user s'il existe en BDD.
     */
    User findByMailAndPassword(String mail,String password);
}
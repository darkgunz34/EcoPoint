package fr.ecopoint.repository;

import fr.ecopoint.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    User findByMail(String mail);

    User findByMailAndPassword(String mail,String password);
    User findByPseudoAndPassword(String pseudo,String password);
}
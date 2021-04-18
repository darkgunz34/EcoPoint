package fr.ecopoint.repository;

import fr.ecopoint.model.entities.Adresse;
import fr.ecopoint.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long > {

}
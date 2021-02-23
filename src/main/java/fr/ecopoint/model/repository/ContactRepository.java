package fr.ecopoint.model.repository;

import fr.ecopoint.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface reliant la BDD et l'entité Message.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long > {
}
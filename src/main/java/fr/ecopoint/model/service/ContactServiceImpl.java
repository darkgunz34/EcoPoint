package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.Contact;
import fr.ecopoint.model.repository.ContactRepository;
import org.springframework.stereotype.Service;

/**
 * Service en charge d'implémenter le CRUD User.
 */
@Service
public class ContactServiceImpl implements ContactService {

    /**
     * Interface pour le CRUD Role
     */
    private final ContactRepository contactRepository;

    /**
     * Constructeur du service
     * @param contactRepository Interface pour le CRUD User
     */
    public ContactServiceImpl(final ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public boolean save(final Contact contact) {
        final Contact newContact = this.contactRepository.save(contact);
        return this.exit(newContact);
    }

    @Override
    public boolean exit(final Contact contact) {
        return this.contactRepository.findById(contact.getId()).isPresent();
    }
}
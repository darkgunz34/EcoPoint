package fr.ecopoint.model.service;

import fr.ecopoint.model.entities.Contact;

public interface ContactService {

    boolean save(final Contact contact);

    boolean exit(final Contact contact);
}
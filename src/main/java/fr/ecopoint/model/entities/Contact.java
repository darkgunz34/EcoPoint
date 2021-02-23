package fr.ecopoint.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Entité en charge de l'ensemble des données de contact pour l'administration.
 */
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String mail;

    @Getter
    @Setter
    String nom;

    @Getter
    @Setter
    String object;

    @Getter
    @Setter
    String message;

    public Contact(final String mail, final String nom, final String object, final String message) {
        this.mail = mail;
        this.nom = nom;
        this.object = object;
        this.message = message;
    }
}

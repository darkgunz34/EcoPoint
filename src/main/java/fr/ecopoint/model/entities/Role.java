package fr.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Le role de l'utilisateur.
 */
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id","name"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String name;

    public Role(final String name){
        this.name = name;
    }
}
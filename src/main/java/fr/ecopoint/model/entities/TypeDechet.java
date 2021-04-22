package fr.ecopoint.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TypeDechet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String nomCategorie;

    @Getter
    @Setter
    String typePoubelle;
}
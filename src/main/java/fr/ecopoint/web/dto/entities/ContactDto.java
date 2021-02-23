package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

/**
 * Class en charge de la récupération des données front vers le back.
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactDto {

    /**
     * Le nom.
     */
    @Getter
    @Setter
    @NotEmpty
    String nom;

    /**
     * L'adresse mail.
     */
    @Getter
    @Setter
    @NotEmpty
    String mail;

    /**
     * L'object du message.
     */
    @Getter
    @Setter
    @NotEmpty
    String objet;

    /**
     * Le corp du message.
     */
    @Getter
    @Setter
    @NotEmpty
    String message;
}

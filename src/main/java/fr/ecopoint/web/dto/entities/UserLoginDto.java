package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

/**
 * Class en charge de la récupération des données front depuis la page de connexion vers le traitement BDD.
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"mail","motDePasse"})
public class UserLoginDto {

    /**
     * L'adresse mail.
     */
    @Getter
    @Setter
    @NotEmpty
    String mail;

    /**
     * Le mot de passe saisie.
     */
    @Getter
    @Setter
    @NotEmpty
    String motDePasse;
}

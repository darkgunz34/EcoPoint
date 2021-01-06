package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

/**
 * Class en charge de la récupération des données front vers le back.
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom","prenom","telephone","adresse","mail","motDePasse","motDePasse2"})
public class UserRegistrationDto {

    /**
     * Le nom.
     */
    @Getter
    @Setter
    @NotEmpty
    String nom;

    /**
     * Le prénom.
     */
    @Getter
    @Setter
    @NotEmpty
    String prenom;

    /**
     * Le numéro de téléphone.
     */
    @Getter
    @Setter
    @NotEmpty
    String telephone;

    /**
     * L'adresse postal.
     */
    @Getter
    @Setter
    @NotEmpty
    String adresse;

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

    /**
     * La confirmation du mot de passe.
     */
    @Getter
    @Setter
    @NotEmpty
    String motDePasse2;
}

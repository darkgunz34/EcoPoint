package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom","prenom","telephone","adresse","mail","ville","codePostal","nouveauMotDePasse","confirmationMotDePasse"})
public class UserModificationDto {

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
     * Ville.
     */
    @Getter
    @Setter
    @NotEmpty
    String ville;

    /**
     * Le nouveau code postal.
     */
    @Getter
    @Setter
    @NotEmpty
    int codePostal;

    /**
     * Le mot de passe saisie.
     */
    @Getter
    @Setter
    @NotEmpty
    String nouveauMotDePasse;

    /**
     * La confirmation du mot de passe.
     */
    @Getter
    @Setter
    @NotEmpty
    String confirmationMotDePasse;
}

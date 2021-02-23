package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

/**
 * Class en charge de la récupération des données BDD pour l'affichage dans l'accueil.
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom","prenom"})
public class UserAccueilDto {

    /**
     * Le nom de la personne.
     */
    @Getter
    @Setter
    @NotEmpty
    String nom;

    /**
     * Le prénom de la personne.
     */
    @Getter
    @Setter
    @NotEmpty
    String prenom;

    /**
     * Le nombre total de point.
     */
    @Getter
    @Setter
    int nombreTotalPoint;
}

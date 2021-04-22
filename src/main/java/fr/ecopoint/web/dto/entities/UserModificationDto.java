package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModificationDto {

    @Getter
    @Setter
    @NotEmpty
    String nom;

    @Getter
    @Setter
    @NotEmpty
    String prenom;

    @Getter
    @Setter
    @NotEmpty
    String telephone;

    @Getter
    @Setter
    @NotEmpty
    String mail;

    @Getter
    @Setter
    @NotEmpty
    String rue;

    @Getter
    @Setter
    @NotEmpty
    String codePostal;

    @Getter
    @Setter
    @NotEmpty
    String ville;
}

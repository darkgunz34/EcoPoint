package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom","prenom"})
public class UserAccueilDto {

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
    int nombreTotalPoint;
}

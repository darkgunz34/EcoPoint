package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"rue","codePostal","ville","pays"})
public class AdresseRegistrationDto {

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

    @Getter
    @Setter
    @NotEmpty
    String pays;
}

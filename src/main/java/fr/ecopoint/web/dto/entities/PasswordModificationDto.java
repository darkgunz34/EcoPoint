package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordModificationDto {

    @Getter
    @Setter
    @NotEmpty
    String motDePasse;

    @Getter
    @Setter
    @NotEmpty
    String confirmationMotDePasse;
}

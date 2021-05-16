package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactDto {

    @Getter
    @Setter
    @NotEmpty
    String nom;

    @Getter
    @Setter
    @NotEmpty
    String mail;

    @Getter
    @Setter
    @NotEmpty
    String objet;

    @Getter
    @Setter
    @NotEmpty
    String message;
}

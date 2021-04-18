package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"mail","motDePasse"})
public class UserLoginDto {

    @Getter
    @Setter
    @NotEmpty
    String mail;

    @Getter
    @Setter
    @NotEmpty
    String motDePasse;
}

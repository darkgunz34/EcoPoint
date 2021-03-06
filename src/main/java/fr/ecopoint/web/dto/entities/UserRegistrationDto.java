package fr.ecopoint.web.dto.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {

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
    String pseudo;

    @Getter
    @Setter
    @NotEmpty
    String motDePasse;

    @Getter
    @Setter
    @NotEmpty
    String motDePasse2;

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

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", motDePasse2='" + motDePasse2 + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}

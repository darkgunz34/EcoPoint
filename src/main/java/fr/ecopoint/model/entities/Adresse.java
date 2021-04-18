package fr.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Table
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id","rue","ville","codePostal"})
@NoArgsConstructor()
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

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

    public Adresse(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
}

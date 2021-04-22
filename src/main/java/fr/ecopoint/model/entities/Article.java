package fr.ecopoint.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String nomArticle;

    @Getter
    @Setter
    String code;

    @Getter
    @Setter
    String description;

    @Getter
    @Setter
    // a saisir a chaque fois
    double prixApproximatif;

    @Getter
    @Setter
    @ManyToOne
    TypeDechet typeDechet;

    //if true visible pour les user sinon a valider par un admin
    @Getter
    boolean visiblePourUser;

    public Article(String nomArticle, String code, double prixApproximatif) {
        this.nomArticle = nomArticle;
        this.code = code;
        this.prixApproximatif = prixApproximatif;
    }
}
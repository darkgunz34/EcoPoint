package fr.ecopoint.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ArticleUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    LocalDate dateAchat;

    @Getter
    @Setter
    @ManyToOne
    Article article;


    //Pour plus tard les 2 attributs.
    @Getter
    @Setter
    @ManyToOne
    PointCollect pointCollect;

    @Getter
    @Setter
    boolean jeter;
}

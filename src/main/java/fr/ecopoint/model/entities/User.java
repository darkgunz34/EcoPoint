package fr.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    @Setter
    String mail;

    @Getter
    @Setter
    String password;

    @Getter
    @Setter
    String nom;

    @Getter
    @Setter
    String prenom;

    @Getter
    @Setter
    String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    Adresse adresse;

    @ManyToOne(cascade=CascadeType.ALL)
    @Getter
    @Setter
    Role role;

    //Inutile pour le moment. Attention au front page accueil
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<PointCollect> lstPointsCollect;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<ArticleUser> lstArticleUser = new ArrayList<>();

    public User(final String mail, final String motDePasse, final String nom, final String prenom, final String telephone, final Role role) {
        this.mail = mail;
        this.password = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.role = role;
    }

    public User(final String mail, final String motDePasse, final Role role) {
        this.mail = mail;
        this.password = motDePasse;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse=" + adresse +
                ", role=" + role +
                ", lstPointsCollect=" + lstPointsCollect +
                ", lstArticleUser=" + lstArticleUser +
                '}';
    }
}
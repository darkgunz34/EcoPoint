package fr.ecopoint.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entité en charge de l'ensemble des données personnel d'un utilisateur.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id","mail","password","nom","prenom","telephone","adresse","role","lstPointsCollect"})
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

    @Getter
    @Setter
    String adresse;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.PERSIST})
    @Getter
    @Setter
    Role role;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<PointCollect> lstPointsCollect;

    /**
     * Constructeur avec les données pré-remplie.
     * @param mail Le mail.
     * @param motDePasse Le mot de passe.
     * @param nom Le nom.
     * @param prenom Le prénom.
     * @param telephone Le numéro de téléphone.
     * @param adresse L'adresse.
     * @param role Le rôle.
     */
    public User(final String mail, final String motDePasse, final String nom, final String prenom, final String telephone, final String adresse, final Role role) {
        this.mail = mail;
        this.password = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = role;
    }

    /**
     * Constructeur par défaut.
     */
    public User(){
    }

    /**
     * Constructeur avec les données pré-remplie pour la connexion à un compte.
     * @param mail Le mail.
     * @param motDePasse Le mot de passe.
     * @param role Le rôle.
     */
    public User(final String mail, final String motDePasse, final Role role) {
        this.mail = mail;
        this.password = motDePasse;
        this.role = role;
    }
}
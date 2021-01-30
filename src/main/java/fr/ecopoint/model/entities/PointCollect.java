package fr.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Les points collecter par un utilisateur.
 */
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id","date","nombrePoint"})
public class PointCollect implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    int nombrePoint;

    @Getter
    LocalDateTime date;
}

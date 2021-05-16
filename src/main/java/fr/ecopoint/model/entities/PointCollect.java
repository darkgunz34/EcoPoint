package fr.ecopoint.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointCollect implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;

    @Getter
    int nombrePoint;

    public PointCollect(int nombrePoint) {
        this.nombrePoint = nombrePoint;
    }
}

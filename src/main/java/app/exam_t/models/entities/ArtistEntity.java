package app.exam_t.models.entities;

import app.exam_t.models.entities.enums.NameOfBandSingerEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private NameOfBandSingerEnum nameOfBandSinger;

    @Column(columnDefinition = "TEXT")
    private String careerInformation;
}

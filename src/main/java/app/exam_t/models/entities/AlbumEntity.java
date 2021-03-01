package app.exam_t.models.entities;

import app.exam_t.models.entities.enums.GenreEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    private String name;

    private String imageUrl;

    private String description;

    private Integer copies;

    private BigDecimal price;

    private LocalDate releaseDate;

    @NotNull
    private String producer;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @ManyToOne
    private ArtistEntity artist;

    @ManyToOne
    private UserEntity addedFrom;
}

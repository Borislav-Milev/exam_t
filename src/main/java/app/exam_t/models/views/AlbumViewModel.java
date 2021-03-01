package app.exam_t.models.views;

import app.exam_t.models.entities.enums.GenreEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AlbumViewModel {
    private String id;
    private String name;
    private String artist;
    private GenreEnum genre;
    private BigDecimal price;
    private LocalDate releaseDate;
    private String imageUrl;
    private String description;
    private Integer copies;
}

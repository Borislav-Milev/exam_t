package app.exam_t.models.services;

import app.exam_t.models.entities.enums.GenreEnum;
import app.exam_t.models.entities.enums.NameOfBandSingerEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AlbumServiceModel {

    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releasedDate;
    private String producer;
    private GenreEnum genre;
    private NameOfBandSingerEnum artist;
    private String addedFrom;
}

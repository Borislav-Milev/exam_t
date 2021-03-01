package app.exam_t.models.models;

import app.exam_t.models.entities.enums.GenreEnum;
import app.exam_t.models.entities.enums.NameOfBandSingerEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AlbumAddBindingModel {

    @Size(min = 3, max = 20, message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "Image cannot be empty.")
    @Size(min = 5, message = "Image must be more than 5 characters.")
    private String imageUrl;

    @NotNull(message = "Price cannot be empty.")
    @DecimalMin(value = "0", message = "Price must be positive number.")
    private BigDecimal price;

    @Min(value = 0, message = "Copies cannot be negative amount.")
    private Integer copies;

    @PastOrPresent(message = "Release date cannot be in the future.")
    @NotNull(message = "Date cannot be empty.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedDate;

    @NotBlank(message = "Producer cannot be empty.")
    private String producer;

    @NotNull(message = "Genre must be selected.")
    private GenreEnum genre;

    private NameOfBandSingerEnum artist;

    private String description;
}

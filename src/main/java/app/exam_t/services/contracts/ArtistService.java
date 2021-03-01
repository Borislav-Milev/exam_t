package app.exam_t.services.contracts;

import app.exam_t.models.entities.ArtistEntity;
import app.exam_t.models.entities.enums.NameOfBandSingerEnum;

public interface ArtistService {
    void implementArtists();

    ArtistEntity getArtist(NameOfBandSingerEnum nameOfBandSingerEnum);
}

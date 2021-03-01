package app.exam_t.repositories;


import app.exam_t.models.entities.ArtistEntity;
import app.exam_t.models.entities.enums.NameOfBandSingerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, String> {

    ArtistEntity findByNameOfBandSinger(NameOfBandSingerEnum nameOfBandSingerEnum);
}

package app.exam_t.repositories;

import app.exam_t.models.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {

    @Query("select sum(a.copies) from AlbumEntity a")
    Integer getTotalCopies();
}

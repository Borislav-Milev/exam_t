package app.exam_t.services.contracts;

import app.exam_t.models.services.AlbumServiceModel;
import app.exam_t.models.views.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    List<AlbumViewModel> getAllAlbums();

    void addAlbum(AlbumServiceModel albumServiceModel);

    void deleteById(String id);

    Integer totalAmountOfSoldCopies();
}

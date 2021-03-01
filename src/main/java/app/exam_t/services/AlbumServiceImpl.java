package app.exam_t.services;

import app.exam_t.models.entities.AlbumEntity;
import app.exam_t.models.services.AlbumServiceModel;
import app.exam_t.models.views.AlbumViewModel;
import app.exam_t.repositories.AlbumRepository;
import app.exam_t.services.contracts.AlbumService;
import app.exam_t.services.contracts.ArtistService;
import app.exam_t.services.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ArtistService artistService;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper,
                            UserService userService, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public List<AlbumViewModel> getAllAlbums() {
        return this.albumRepository.findAll()
                .stream()
                .map(albumEntity -> {
                    AlbumViewModel albumViewModel = this.modelMapper.map(albumEntity, AlbumViewModel.class);
                    albumViewModel.setArtist(albumEntity.getArtist().getCareerInformation());
                    return albumViewModel;
                }).collect(Collectors.toList());
    }


    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = this.modelMapper.map(albumServiceModel, AlbumEntity.class);
        albumEntity.setAddedFrom(this.userService.getUser(albumServiceModel.getAddedFrom()));
        albumEntity.setArtist(artistService.getArtist(albumServiceModel.getArtist()));
        this.albumRepository.saveAndFlush(albumEntity);
    }

    @Override
    public void deleteById(String id) {
        this.albumRepository.deleteById(id);
    }

    @Override
    public Integer totalAmountOfSoldCopies() {
        return this.albumRepository.getTotalCopies();
    }


}

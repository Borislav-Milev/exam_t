package app.exam_t.services;

import app.exam_t.models.entities.UserEntity;
import app.exam_t.models.models.UserRegisterBindingModel;
import app.exam_t.models.services.UserLoginServiceModel;
import app.exam_t.models.services.UserRegisterServiceModel;
import app.exam_t.repositories.UserRepository;
import app.exam_t.services.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(UserRegisterServiceModel model) {
        UserEntity entity = this.modelMapper.map(model, UserEntity.class);
        if (this.userRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword()).isPresent()) {
            return false;
        }
        this.userRepository.saveAndFlush(entity);
        return true;
    }

    @Override
    public boolean checkIfUserExists(UserLoginServiceModel model) {
        return this.userRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword()).isPresent();
    }

    @Override
    public UserEntity getUser(String username) {
        return this.userRepository.findByUsername(username);
    }
}

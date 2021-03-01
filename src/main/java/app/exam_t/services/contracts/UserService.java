package app.exam_t.services.contracts;

import app.exam_t.models.entities.UserEntity;
import app.exam_t.models.services.UserLoginServiceModel;
import app.exam_t.models.services.UserRegisterServiceModel;

public interface UserService {

    boolean registerUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean checkIfUserExists(UserLoginServiceModel userLoginServiceModel);

    UserEntity getUser(String username);
}

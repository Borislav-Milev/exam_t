package app.exam_t.models.services;

import lombok.Data;

@Data
public class UserRegisterServiceModel {

    private String username;
    private String password;
    private String fullName;
    private String email;
}

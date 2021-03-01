package app.exam_t.models.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserLoginBindingModel {

    @Size(min = 2, max = 20, message = "Username must be between 3 and 20 characters.")
    @NotEmpty(message = "Username cannot be empty.")
    private String username;

    @Size(min = 2, max = 20, message = "Password must be between 3 and 20 characters.")
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}

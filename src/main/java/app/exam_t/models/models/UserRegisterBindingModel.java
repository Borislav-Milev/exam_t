package app.exam_t.models.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterBindingModel {

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;

    @NotBlank(message = "Full name cannot be empty.")
    @Size(min = 3, max = 20, message = "Full name must be between 3 and 20 characters.")
    private String fullName;

    @Email(message = "Please enter valid email.")
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters.")
    private String password;

    @NotBlank(message = "Please confirm password")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters.")
    private String confirmPassword;
}

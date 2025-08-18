package project4.petShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotEmpty(message = "please fill your full name in")
    @Size(min = 5, max = 100 , message = "full name must be from 5 to 100 characters")
    private String fullName;

    @Email
    @NotEmpty(message = "please fill your email in")
    @Size(min = 5, max = 30 , message = "email must be from 5 to 30 characters")
    private String email;

    @NotEmpty(message = "login must be filled")
    @Size(min = 3, max = 20 , message = "login must be from 3 to 20 characters")
    private String login;

    @NotEmpty(message = "password must be filled")
    @Size(min = 3, max = 20 , message = "password must be from 3 to 20 characters")
    private String password;


}

package project4.petShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project4.petShop.models.Pet;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "please fill your full name in")
    @Size(min = 5, max = 100 , message = "full name must be from 6 to 100 characters")
    private String fullName;

    @Email
    @NotEmpty(message = "please fill your email in")
    @Size(min = 5, max = 30 , message = "email must be from 5 to 30 characters")
    private String email;
    private List<Pet> pets = new ArrayList<>();

    @Builder
    public UserDTO(String fullName, String email, List<Pet> pets) {
        this.fullName = fullName;
        this.email = email;
        this.pets = pets;
    }
}

package project4.petShop.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project4.petShop.models.Event;
import project4.petShop.models.User;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PetDTO {

    @NotEmpty(message = "Кличка не должна быть пустой")
    @Size(min = 2, max = 100, message = "Кличка должна быть от 2 до 100 символов длиной")
    private String nickname;

    private PetType petType;

    private Date dateOfBirth;

    private PetStatus petStatus;

    private User owner;

}

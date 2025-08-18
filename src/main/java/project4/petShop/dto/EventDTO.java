package project4.petShop.dto;

import lombok.Builder;
import lombok.Data;
import project4.petShop.models.Pet;
import project4.petShop.util.PetEventType;

import java.util.Date;
@Data
public class EventDTO {
    private PetEventType type;
    private Date date;
    private Pet pet;

    @Builder
    public EventDTO(PetEventType type, Date date, Pet pet) {
        this.type = type;
        this.date = date;
        this.pet = pet;
    }
}

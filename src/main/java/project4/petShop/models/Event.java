package project4.petShop.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project4.petShop.util.PetEventType;

import java.util.Date;

@Table
@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_event")
    private PetEventType type;

    @Column(name = "date_of_event")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "pet_in_event") // имя столбца, как в БД
    private Pet pet;

    @Builder
    public Event(PetEventType type, Date date, Pet pet) {
        this.type = type;
        this.date = date;
        this.pet = pet;
    }
}

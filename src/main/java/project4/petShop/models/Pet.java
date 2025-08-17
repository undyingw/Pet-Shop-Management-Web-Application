package project4.petShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "nickname")
    @NotEmpty(message = "Кличка не должна быть пустой")
    @Size(min = 2, max = 100, message = "Кличка должна быть от 2 до 100 символов длиной")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PetType petType;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PetStatus petStatus;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(mappedBy = "pet", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @Builder // позволяет удобно создавать объект через билдер
    public Pet(String nickname, PetType petType, Date dateOfBirth, PetStatus petStatus, User owner) {
        this.nickname = nickname;
        this.petType = petType;
        this.dateOfBirth = dateOfBirth;
        this.petStatus = petStatus;
        this.owner = owner;
    }

}

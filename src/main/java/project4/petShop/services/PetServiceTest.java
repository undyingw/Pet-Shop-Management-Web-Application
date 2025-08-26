package project4.petShop.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.Pet;
import project4.petShop.models.User;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;
import project4.petShop.util.UserRoles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PetServiceTest {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @Test
    void testAddAndFindPet() {
        User owner = User.builder()
                .login("owner1")
                .password("12345")
                .fullName("Иван Иванов")
                .email("ivan@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(owner);

        Pet pet = Pet.builder()
                .nickname("Тузик")
                .petType(PetType.DOG)
                .dateOfBirth(new Date())
                .petStatus(PetStatus.AT_HOME)
                .owner(owner)
                .build();

        petService.addPet(pet);

        Pet found = petService.findById(pet.getId());
        assertNotNull(found);
        assertEquals("Тузик", found.getNickname());
        assertEquals(PetType.DOG, found.getPetType());
        assertEquals(owner.getLogin(), found.getOwner().getLogin());
    }

    @Test
    void testUpdatePet() {
        User owner = User.builder()
                .login("owner2")
                .password("54321")
                .fullName("Петр Петров")
                .email("petr@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(owner);

        Pet pet = Pet.builder()
                .nickname("Барсик")
                .petType(PetType.CAT)
                .petStatus(PetStatus.AT_HOME)
                .dateOfBirth(new Date())
                .owner(owner)
                .build();
        petService.addPet(pet);

        int id = pet.getId();
        pet.setNickname("Мурзик");
        petService.updatePet(id, pet);

        Pet updated = petService.findById(id);
        assertEquals("Мурзик", updated.getNickname());
    }

    @Test
    void testDeletePet() {
        User owner = User.builder()
                .login("owner3")
                .password("pw")
                .fullName("Сидоров Сидор")
                .email("sid@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(owner);

        Pet pet = Pet.builder()
                .nickname("Чижик")
                .petType(PetType.PARROT)
                .petStatus(PetStatus.AT_HOME)
                .dateOfBirth(new Date())
                .owner(owner)
                .build();
        petService.addPet(pet);

        int id = pet.getId();
        petService.deletePet(id);

        assertNull(petService.findById(id));
    }

    @Test
    void testFindByStatus() {
        User owner = User.builder()
                .login("owner4")
                .password("pw4")
                .fullName("Мария Иванова")
                .email("maria@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(owner);

        Pet pet1 = Pet.builder()
                .nickname("Рекс")
                .petType(PetType.DOG)
                .petStatus(PetStatus.LOST)
                .dateOfBirth(new Date())
                .owner(owner)
                .build();
        petService.addPet(pet1);

        Pet pet2 = Pet.builder()
                .nickname("Лапка")
                .petType(PetType.CAT)
                .petStatus(PetStatus.AT_HOME)
                .dateOfBirth(new Date())
                .owner(owner)
                .build();
        petService.addPet(pet2);

        List<Pet> lostPets = petService.findByStatus(PetStatus.LOST);

        assertEquals(1, lostPets.size());
        assertEquals("Рекс", lostPets.get(0).getNickname());
    }
}
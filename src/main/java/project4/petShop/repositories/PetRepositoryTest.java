package project4.petShop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project4.petShop.models.Pet;
import project4.petShop.models.User;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;
import project4.petShop.util.UserRoles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindByStatus() {
        User owner = User.builder()
                .login("sergey")
                .password("pass123")
                .fullName("Сергей Сергеев")
                .email("sergey@example.com")
                .role(UserRoles.USER)
                .build();
        userRepository.save(owner);

        Pet pet = Pet.builder()
                .nickname("Мурзик")
                .petType(PetType.CAT)
                .dateOfBirth(new Date())
                .petStatus(PetStatus.LOST)
                .owner(owner)
                .build();
        petRepository.save(pet);

        List<Pet> found = petRepository.findByPetStatus(PetStatus.LOST);

        assertEquals(1, found.size());
        assertEquals("Мурзик", found.get(0).getNickname());
        assertEquals(owner.getLogin(), found.get(0).getOwner().getLogin());
    }
}
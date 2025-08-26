package project4.petShop.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.Event;
import project4.petShop.models.Pet;
import project4.petShop.models.User;
import project4.petShop.util.PetEventType;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;
import project4.petShop.util.UserRoles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @Test
    void testAddAndFindEvent() {
        User user = User.builder()
                .login("vasya")
                .password("pw")
                .fullName("Василий Петров")
                .email("vasya@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(user);

        Pet pet = Pet.builder()
                .nickname("Белка")
                .petType(PetType.DOG)
                .dateOfBirth(new Date())
                .petStatus(PetStatus.AT_HOME)
                .owner(user)
                .build();
        petService.addPet(pet);

        Event event = Event.builder()
                .type(PetEventType.LOST)
                .date(new Date())
                .pet(pet)
                .build();
        eventService.addEvent(event);

        Event found = eventService.findById(event.getId());
        assertNotNull(found);
        assertEquals(PetEventType.LOST, found.getType());
        assertEquals("Белка", found.getPet().getNickname());
    }

    @Test
    void testDeleteEvent() {
        User user = User.builder()
                .login("ivan")
                .password("pw")
                .fullName("Иван Иванов")
                .email("ivan@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(user);

        Pet pet = Pet.builder()
                .nickname("Котик")
                .petType(PetType.CAT)
                .dateOfBirth(new Date())
                .petStatus(PetStatus.AT_HOME)
                .owner(user)
                .build();
        petService.addPet(pet);

        Event event = Event.builder()
                .type(PetEventType.DIED)
                .date(new Date())
                .pet(pet)
                .build();
        eventService.addEvent(event);

        int id = event.getId();
        eventService.deleteEvent(id);

        assertNull(eventService.findById(id));
    }
}
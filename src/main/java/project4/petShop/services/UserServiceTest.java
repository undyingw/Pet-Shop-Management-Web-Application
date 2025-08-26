package project4.petShop.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.User;
import project4.petShop.util.UserRoles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddAndFindUser() {
        User user = User.builder()
                .login("alex")
                .password("12345")
                .fullName("Алексей Алексеев")
                .email("alex@example.com")
                .role(UserRoles.USER)
                .build();

        userService.addUser(user);

        User found = userService.findByLogin("alex");
        assertNotNull(found);
        assertEquals("Алексей Алексеев", found.getFullName());
    }

    @Test
    void testUpdateUser() {
        User user = User.builder()
                .login("olga")
                .password("1111")
                .fullName("Ольга Петрова")
                .email("olga@example.com")
                .role(UserRoles.USER)
                .build();

        userService.addUser(user);
        int id = user.getId();

        user.setFullName("Ольга Иванова");
        userService.updateUser(id, user);

        User updated = userService.findById(id);
        assertEquals("Ольга Иванова", updated.getFullName());
    }

    @Test
    void testDeleteUser() {
        User user = User.builder()
                .login("testuser")
                .password("pw")
                .fullName("Тест Тестов")
                .email("test@example.com")
                .role(UserRoles.USER)
                .build();

        userService.addUser(user);
        int id = user.getId();

        userService.deleteUser(id);

        assertNull(userService.findById(id));
    }
}
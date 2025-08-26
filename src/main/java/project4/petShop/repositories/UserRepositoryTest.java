package project4.petShop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project4.petShop.models.User;
import project4.petShop.util.UserRoles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindByLogin() {
        User user = User.builder()
                .login("testlogin")
                .password("pw")
                .fullName("Test User")
                .email("test@example.com")
                .role(UserRoles.USER)
                .build();

        userRepository.save(user);

        Optional<User> found = userRepository.findByLogin("testlogin");

        assertTrue(found.isPresent());
        assertEquals("Test User", found.get().getFullName());
    }

    @Test
    void testFindByFullNameStartingWith() {
        User user = User.builder()
                .login("anna")
                .password("1234")
                .fullName("Anna Karenina")
                .email("anna@example.com")
                .role(UserRoles.USER)
                .build();
        userRepository.save(user);

        assertFalse(userRepository.findByFullNameStartingWith("Ann").isEmpty());
        assertTrue(userRepository.findByFullNameStartingWith("Pet").isEmpty());
    }
}
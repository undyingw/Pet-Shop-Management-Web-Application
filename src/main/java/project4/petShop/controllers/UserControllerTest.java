package project4.petShop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project4.petShop.models.User;
import project4.petShop.services.UserService;
import project4.petShop.util.UserRoles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    void testShowUser() throws Exception {
        User user = User.builder()
                .login("petya")
                .password("pw")
                .fullName("Петр Петров")
                .email("petya@example.com")
                .role(UserRoles.USER)
                .build();
        userService.addUser(user);

        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/show"))
                .andExpect(model().attributeExists("user"));
    }
}
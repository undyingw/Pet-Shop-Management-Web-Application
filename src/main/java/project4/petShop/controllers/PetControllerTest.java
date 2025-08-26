package project4.petShop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project4.petShop.models.User;
import project4.petShop.repositories.UserRepository;
import project4.petShop.util.UserRoles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreatePetForm() throws Exception {
        mockMvc.perform(get("/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/new"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("statuses"));
    }

    @Test
    void testCreatePetAndRedirect() throws Exception {
        // нужно чтобы был владелец
        User owner = User.builder()
                .login("ivan")
                .password("12345")
                .fullName("Иван Иванов")
                .email("ivan@example.com")
                .role(UserRoles.USER)
                .build();
        userRepository.save(owner);

        mockMvc.perform(post("/pets/new")
                        .param("nickname", "Тузик")
                        .param("petType", "DOG")
                        .param("petStatus", "AT_HOME")
                        .param("owner.id", String.valueOf(owner.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pets"));
    }
}
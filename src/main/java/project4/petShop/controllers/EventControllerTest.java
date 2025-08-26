package project4.petShop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project4.petShop.models.User;
import project4.petShop.repositories.UserRepository;
import project4.petShop.util.UserRoles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/events/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/new"))
                .andExpect(model().attributeExists("event"))
                .andExpect(model().attributeExists("eventType"))
                .andExpect(model().attributeExists("pets"));
    }

    @Test
    void testIndexEvents() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/index"))
                .andExpect(model().attributeExists("events"));
    }
}
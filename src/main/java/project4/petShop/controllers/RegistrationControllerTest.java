package project4.petShop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSuccessfulRegistration() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("fullName", "Василий Пупкин")
                        .param("email", "vasya@example.com")
                        .param("login", "vasya")
                        .param("password", "12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login"));
    }

    @Test
    void testValidationError() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("fullName", "") // ошибка
                        .param("email", "bademail")
                        .param("login", "va")
                        .param("password", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"));
    }
}
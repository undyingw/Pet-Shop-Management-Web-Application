package project4.petShop.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project4.petShop.dto.UserRegistrationDTO;
import project4.petShop.models.User;
import project4.petShop.services.RegistrationService;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    public RegistrationController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "auth/register";
    }

    @PostMapping("/register")
    public String saveRegistration(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        registrationService.register(modelMapper.map(userRegistrationDTO, User.class));
        return "redirect:/auth/login";
    }
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

}

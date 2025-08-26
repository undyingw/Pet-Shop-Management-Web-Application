package project4.petShop.controllers;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project4.petShop.dto.UserDTO;
import project4.petShop.dto.UserRegistrationDTO;
import project4.petShop.models.User;
import project4.petShop.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findByIdWithPets(id));

        return "users/show";
    }

//    @GetMapping("/new")
//    public String create(Model model) {
//        model.addAttribute("user", new User());
//        return "users/new";
//    }

//    @PostMapping("/new")
//    public String create(@ModelAttribute @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "users/new";
//        }
//        userService.addUser(user);
//        return "redirect:/users";
//    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PreAuthorize("hasRole('ADMIN') or #pet.owner.login == authentication.name")
    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    public User userDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO userToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User userRegistrationDTOToUser(UserRegistrationDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}

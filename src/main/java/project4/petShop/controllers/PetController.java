package project4.petShop.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project4.petShop.models.Pet;
import project4.petShop.services.PetService;
import project4.petShop.services.UserService;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final UserService userService;

    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String index(Model model) {
        model.addAttribute("pets", petService.findAll());

        return "pets/index";
    }
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("types", PetType.values());
        model.addAttribute("statuses", PetStatus.values());
        return "pets/new";
    }
    @PostMapping("/new")
    public String create(@Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "pets/new";
        }
        petService.addPet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("pet", petService.findById(id));

        return "pets/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("types", PetType.values());
        model.addAttribute("statuses", PetStatus.values());
        return "pets/edit";
    }

    @PreAuthorize("hasRole('ADMIN') or #pet.owner.login == authentication.name")
    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/edit";
        }
        petService.updatePet(id, pet);
        return "redirect:/pets";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        petService.deletePet(id);
        return "redirect:/pets";
    }

    @GetMapping("/{id}/changeStatus")
    public String changeStatusForm(@PathVariable int id, Model model) {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("statuses", PetStatus.values());
        return "pets/changeStatus";
    }

    @PostMapping("/{id}/changeStatus")
    public String changeStatus(@PathVariable int id, @RequestParam PetStatus status) {
        Pet pet = petService.findById(id);
        if (pet != null) {
            pet.setPetStatus(status);
            petService.updatePet(id, pet);
        }
        return "redirect:/pets/" + id;
    }

    @GetMapping("/filter")
    public String filterByStatus(@RequestParam PetStatus status, Model model) {
        model.addAttribute("pets", petService.findByStatus(status));
        model.addAttribute("statuses", PetStatus.values());
        return "pets/index";
    }
}

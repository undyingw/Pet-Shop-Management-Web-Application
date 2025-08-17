package project4.petShop.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project4.petShop.models.Event;
import project4.petShop.models.Pet;
import project4.petShop.services.EventService;
import project4.petShop.services.PetService;
import project4.petShop.util.PetEventType;
import project4.petShop.util.PetStatus;
import project4.petShop.util.PetType;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final PetService petService;

    public EventController(EventService eventService, PetService petService) {
        this.eventService = eventService;
        this.petService = petService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("events", eventService.findAll());

        return "events/index";
    }
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventType", PetEventType.values());
        model.addAttribute("pets", petService.findAll());
        return "events/new";
    }
    @PostMapping("/new")
    public String create(@Valid Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "events/new";
        }
        eventService.addEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("event", eventService.findById(id));

        return "events/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        model.addAttribute("eventType", PetEventType.values());
        model.addAttribute("pets", petService.findAll());

        return "events/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "events/edit";
        }
        eventService.update(id, event);
        return "redirect:/events";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
}

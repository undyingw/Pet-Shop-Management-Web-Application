package project4.petShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.Event;
import project4.petShop.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void update(int id, Event event) {
        event.setId(id);
        eventRepository.save(event);
    }


    @Transactional
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    public Event findById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}

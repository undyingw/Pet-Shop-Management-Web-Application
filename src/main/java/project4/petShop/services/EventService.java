package project4.petShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.Event;
import project4.petShop.repositories.EventRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void editEvent(int id, Event event) {
        event.setId(id);
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id).get();
    }

    public List<Event> findAll(int id) {
        return eventRepository.findAll();
    }
}

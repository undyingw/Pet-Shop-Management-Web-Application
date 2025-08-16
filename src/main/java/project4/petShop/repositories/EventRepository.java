package project4.petShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project4.petShop.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}

package project4.petShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project4.petShop.models.Pet;
import project4.petShop.util.PetStatus;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByPetStatus(PetStatus status);
}

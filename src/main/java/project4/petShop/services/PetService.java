package project4.petShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.Pet;
import project4.petShop.repositories.PetRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PetService {

    private PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public void addPet(Pet pet) {
        petRepository.save(pet);
    }

    @Transactional
    public void updatePet(int id, Pet pet) {
        pet.setId(id);
        petRepository.save(pet);
    }

    @Transactional
    public void deletePet(int id) {
        petRepository.deleteById(id);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(int id) {
        return petRepository.findById(id).orElse(null);
    }
}

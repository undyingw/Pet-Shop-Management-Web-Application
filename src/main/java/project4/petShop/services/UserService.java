package project4.petShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project4.petShop.models.User;
import project4.petShop.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByIdWithPets(int id) {
        return userRepository.findByIdWithPets(id).orElse(null);
    }

    public List<User> findByFullNameStartingWith(String fullName){
        return userRepository.findByFullNameStartingWith(fullName);
    }


}

package project4.petShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project4.petShop.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.pets WHERE u.id = :id")
    Optional<User> findByIdWithPets(@Param("id") int id);
    List<User> findByFullNameStartingWith(String fullName);
    Optional<User> findByLogin(String login);
}

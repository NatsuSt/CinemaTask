package ua.nure.cinematask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.cinematask.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

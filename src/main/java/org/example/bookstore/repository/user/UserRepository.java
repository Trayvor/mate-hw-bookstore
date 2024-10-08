package org.example.bookstore.repository.user;

import java.util.Optional;
import org.example.bookstore.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @EntityGraph("roles")
    Optional<User> findByEmail(String email);
}

package ru.seva.tasklist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seva.tasklist.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

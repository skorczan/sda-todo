package pl.sda.example.todo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    Set<User> findByLogin(String login);
}

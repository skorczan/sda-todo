package pl.sda.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.example.todo.api.UserDto;
import pl.sda.example.todo.entity.User;
import pl.sda.example.todo.entity.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserConverter converter;

    private final PasswordEncoder passwordEncoder;

    public Optional<UserDto> getOne(int id) {
        return repository.findById(id).map(converter::map);
    }

    public Page<UserDto> find(PageRequest page) {
        return repository.findAll(page).map(converter::map);
    }

    public UserDto register(String username, String password) {
        if (repository.findByLogin(username).isPresent()) {
            throw new UsernameAlreadyTaken(username);
        }

        val user = new User();
        user.setLogin(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        return converter.map(repository.saveAndFlush(user));
    }
}

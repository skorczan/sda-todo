package pl.sda.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.example.todo.api.UserDto;
import pl.sda.example.todo.entity.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserConverter converter;

    public Optional<UserDto> getOne(int id) {
        return repository.findById(id).map(converter::map);
    }
}

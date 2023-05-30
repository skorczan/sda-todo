package pl.sda.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.example.todo.api.ToDoDto;
import pl.sda.example.todo.entity.ToDoRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository repository;

    private final ToDoConverter converter;

    public Optional<ToDoDto> getOne(int id) {
        return repository.findById(id).map(converter::map);
    }
}

package pl.sda.example.todo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.sda.example.todo.api.*;
import pl.sda.example.todo.entity.ToDo;
import pl.sda.example.todo.entity.ToDoRepository;
import pl.sda.example.todo.entity.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository repository;

    private final UserRepository userRepository;

    private final ToDoConverter converter;

    public Optional<ToDoDto> getOne(int id) {
        return repository.findById(id).map(converter::map);
    }

    public ToDoDto add(CreateTodoDto dto, int ownerId) {
        val entity = new ToDo();
        entity.setName(dto.getName());
        entity.setDetails(dto.getDetails());

        userRepository.findById(ownerId)
                .ifPresentOrElse(entity::setOwner, () -> {
                    throw new UserNotFound(ownerId);
                });

        return converter.map(repository.save(entity));
    }

    public ToDoDto update(UpdateTodoDto dto, int ownerId) {
        val entity = repository.findById(dto.getId()).orElseThrow(() -> new TodoNotFound(dto.getId()));
        entity.setName(dto.getName());
        entity.setDetails(dto.getDetails());

        userRepository.findById(ownerId)
                .ifPresentOrElse(entity::setOwner, () -> {
                    throw new TodoOwnerNotFound(dto.getId(), ownerId);
                });

        return converter.map(repository.save(entity));
    }
}

package pl.sda.example.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.example.todo.service.ToDoService;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @GetMapping("/{id}")
    public Optional<ToDoDto> getOne(@PathVariable("id") int id) {
        return service.getOne(id);
    }
}

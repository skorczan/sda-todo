package pl.sda.example.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.example.todo.service.ToDoService;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> getOne(@PathVariable("id") int id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

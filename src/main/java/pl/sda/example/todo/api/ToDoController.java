package pl.sda.example.todo.api;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.sda.example.todo.service.ToDoService;

import java.net.URI;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    private final int ownerId = 1; // TODO: get it from security

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> getOne(@PathVariable("id") Integer id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ToDoDto> addOne(@RequestBody CreateTodoDto dto) {
        try {
            val result = service.add(dto, ownerId);

            return ResponseEntity.created(locationOf(result.getId())).body(result);
        } catch (UserNotFound e) {
            return ResponseEntity.internalServerError().build(); // TODO: do it better
        }
    }

    private URI locationOf(int id) {
        try {
            return MvcUriComponentsBuilder.fromMethod(
                    ToDoController.class,
                    ToDoController.class.getMethod("getOne", Integer.class),
                    id)
                    .build()
                    .toUri();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("method is defined");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoDto> updateOne(@PathVariable("id") int id, @RequestBody CreateTodoDto payload) {
        try {
            val dto = new UpdateTodoDto();
            dto.setId(id);
            dto.setName(payload.getName());
            dto.setDetails(payload.getDetails());

            return ResponseEntity.ok(service.update(dto, ownerId));
        } catch (TodoNotFound ex) {
            return ResponseEntity.notFound().build();
        } catch (TodoOwnerNotFound ex) {
            return ResponseEntity.internalServerError().build(); // TODO: do it better
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ToDoDto> deleteOne(@PathVariable("id") int id) {
//        return service.getOne(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

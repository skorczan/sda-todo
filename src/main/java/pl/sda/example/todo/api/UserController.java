package pl.sda.example.todo.api;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sda.example.todo.service.UserService;
import pl.sda.example.todo.service.UsernameAlreadyTaken;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable("id") int id) {
        return service.getOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<Page<UserDto>> list(PageRequest page) {
        return ResponseEntity.ok(service.find(page));
    }

    @PostMapping("")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterUser dto) {
        try {
            val user = service.register(dto.getLogin(), dto.getPassword());
            return ResponseEntity.ok(user);
        } catch (UsernameAlreadyTaken ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ExceptionHandler
    protected ResponseEntity<ValidationError> validationException(ValidationException exception) {
        return ResponseEntity.badRequest().body(new ValidationError(exception.getMessage()));
    }
}
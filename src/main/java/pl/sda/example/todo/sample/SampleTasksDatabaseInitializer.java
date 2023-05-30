package pl.sda.example.todo.sample;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.example.todo.api.CreateTodoDto;
import pl.sda.example.todo.entity.User;
import pl.sda.example.todo.entity.UserRepository;
import pl.sda.example.todo.service.ToDoService;

@Component
@Transactional
@RequiredArgsConstructor
public class SampleTasksDatabaseInitializer implements CommandLineRunner {

    private final ToDoService service;

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        val user = sampleUser();

        service.add(new CreateTodoDto("Task 1",  "Description of task 1"), user.getId());
        service.add(new CreateTodoDto("Task 2",  "Description of task 2"), user.getId());
        service.add(new CreateTodoDto("Task 3",  "Description of task 3"), user.getId());
        service.add(new CreateTodoDto("Task 4",  "Description of task 4"), user.getId());
        service.add(new CreateTodoDto("Task 5",  "Description of task 5"), user.getId());
    }

    private User sampleUser() {
        val user = new User();
        user.setLogin("example");
        return userRepository.saveAndFlush(user);
    }
}

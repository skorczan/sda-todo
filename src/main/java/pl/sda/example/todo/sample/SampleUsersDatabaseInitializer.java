package pl.sda.example.todo.sample;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.sda.example.todo.entity.UserRepository;
import pl.sda.example.todo.service.UserService;
import pl.sda.example.todo.service.UsernameAlreadyTaken;

@Profile("demo")
@Component
@Transactional
@RequiredArgsConstructor
public class SampleUsersDatabaseInitializer implements CommandLineRunner {

    private final UserService service;

    @Override
    public void run(String... args) throws Exception {
        try {
            service.register("admin", "admin");
        } catch (UsernameAlreadyTaken ex) {
        }

        try {
            service.register("user", "user");
        } catch (UsernameAlreadyTaken ex) {
        }
    }
}

package pl.sda.example.todo.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUser {

    @NotNull
    private String login;

    @NotNull
    private String password;
}

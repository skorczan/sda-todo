package pl.sda.example.todo.api;

import lombok.Value;

@Value
public class UserNotFound extends RuntimeException {

    private final int id;
}

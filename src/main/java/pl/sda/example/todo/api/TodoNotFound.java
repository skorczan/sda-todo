package pl.sda.example.todo.api;

import lombok.Value;

@Value
public class TodoNotFound extends RuntimeException {

    private final int id;
}

package pl.sda.example.todo.api;

import lombok.Data;

@Data
public class UpdateTodoDto {

    private int id;

    private String name;

    private String details;
}

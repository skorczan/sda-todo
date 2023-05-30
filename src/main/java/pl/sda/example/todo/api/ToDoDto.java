package pl.sda.example.todo.api;

import lombok.Data;

@Data
public class ToDoDto {

    private Integer id;

    private String name;

    private String details;

    private Integer ownerId;
}

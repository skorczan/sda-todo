package pl.sda.example.todo.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class CreateTodoDto {

    private String name;

    private String details;

    public CreateTodoDto() {
    }

    @JsonCreator
    public CreateTodoDto(String name, String details) {
        this.name = name;
        this.details = details;
    }
}

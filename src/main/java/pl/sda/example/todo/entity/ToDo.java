package pl.sda.example.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ToDo {

    @Id
    private Integer id;

    private String name;

    private String details;

    @ManyToOne(optional = false)
    private User owner;
}

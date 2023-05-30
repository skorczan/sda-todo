package pl.sda.example.todo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String details;

    @ManyToOne(optional = false)
    private User owner;
}

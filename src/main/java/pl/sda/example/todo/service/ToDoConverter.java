package pl.sda.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.sda.example.todo.api.ToDoDto;
import pl.sda.example.todo.entity.ToDo;

@Component
@Transactional
public class ToDoConverter {

    public ToDoDto map(ToDo entity) {
        val dto = new ToDoDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDetails(entity.getDetails());
//        dto.setOwnerId(entity.getOwner().getId());
        return dto;
    }
}

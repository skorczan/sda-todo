package pl.sda.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.stereotype.Component;
import pl.sda.example.todo.api.UserDto;
import pl.sda.example.todo.entity.User;

@Component
@Transactional
public class UserConverter {

    public UserDto map(User entity) {
        val dto = new UserDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        return dto;
    }
}

package pl.sda.example.todo.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.example.todo.entity.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                        .map(user -> new User(
                        user.getLogin(),
                        user.getPasswordHash(),
                        List.of(new SimpleGrantedAuthority("USER")))
                ).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

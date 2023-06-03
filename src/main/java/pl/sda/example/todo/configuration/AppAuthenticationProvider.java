//package pl.sda.example.todo.configuration;
//
//import lombok.RequiredArgsConstructor;
//import lombok.val;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import pl.sda.example.todo.entity.UserRepository;
//
//import java.util.Objects;
//
//// @Service
//@RequiredArgsConstructor
//public class AppAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserRepository repository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if (!this.supports(authentication.getClass())) {
//            return null;
//        }
//
//        if (authentication.isAuthenticated()) {
//            return authentication;
//        }
//
//        val login = authentication.getPrincipal().toString();
//        val passwordHash = passwordEncoder.encode(authentication.getCredentials().toString());
//        val users = repository.findByLogin(login);
//
//        switch (users.size()) {
//            case 0:
//                throw new UsernameNotFoundException(login);
//            case 1:
//                val user = users.iterator().next();
//
//                if (Objects.equals(user.getLogin(), login) && Objects.equals(user.getPasswordHash(), passwordHash)) {
//                    return authentication;
//                } else {
//                    throw new BadCredentialsException("for user " + login);
//                }
//            default:
//                throw new IllegalStateException("more than one user with login " + login);
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
//    }
//}

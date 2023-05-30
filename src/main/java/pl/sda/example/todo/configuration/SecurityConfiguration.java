package pl.sda.example.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
            requests.anyRequest().permitAll();
        });

        http.csrf(csrf -> {
            csrf.disable();
        });

        http.cors(cors -> {
            cors.disable();
        });

        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.headers(headers -> {
            headers.frameOptions(frameOptions -> frameOptions.disable());
        });

        return http.build();
    }
}

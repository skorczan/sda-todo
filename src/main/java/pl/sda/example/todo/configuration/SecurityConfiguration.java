package pl.sda.example.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
//            requests.anyRequest().permitAll();
            requests.requestMatchers("/tasks", "/tasks/**").hasAuthority("USER");
            requests.requestMatchers("/users", "/users/**").hasAuthority("USER");
            requests.requestMatchers("/h2-console", "/h2-console/**").permitAll();
        });

        http.csrf(csrf -> {
            csrf
                    .ignoringRequestMatchers("/h2-console", "/h2-console/**")
                    .disable();
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

         http.httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

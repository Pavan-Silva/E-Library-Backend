package lk.elib.elibrarybackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/api/auth/**").permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/books").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/search/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("MEMBER", "EMPLOYEE", "MANAGER")

                                .requestMatchers("/api/staff/**").hasRole("MANAGER")
                                .requestMatchers("/api/members/**").hasAnyRole("EMPLOYEE", "MANAGER")
                                .anyRequest().authenticated()
                                    .and()
                                    .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                        );

        return http.build();
    }
}

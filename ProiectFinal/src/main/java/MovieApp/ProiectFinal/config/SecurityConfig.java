package MovieApp.ProiectFinal.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
//    private final Logger logger = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
//        logger.info("Configuring security filter chain...");
        http

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/register", "/").permitAll()
                        .requestMatchers("/resources/static/**", "/static/**").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/admin", "/allUsers", "/users/delete", "/movie/delete", "/movie/addMovie",
                                        "/series/delete", "/series/addSeries", "/genre/show", "/genre/delete", "/genre/save").hasAuthority("ADMIN")
//                        .requestMatchers(PathRequest.toStaticResources().at(StaticResourceLocation.CSS)).permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new AdminAuthHandler())
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
//        logger.info("Security filter chain configured successfully.");
        return http.build();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}

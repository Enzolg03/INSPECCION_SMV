package pe.edu.cibertec.APPDAWIPROYECTO.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import pe.edu.cibertec.APPDAWIPROYECTO.service.DetalleUsuarioService;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private DetalleUsuarioService detalleUsuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detalleUsuarioService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/auth/login",
                                                "/auth/registrar",
                                                "/auth/guardarusuario",
                                                "/resources/**",
                                                "/static/**",
                                                "/styles/**",
                                                "/scripts/**").permitAll()
                                        .requestMatchers("/auth/frmusuario").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                )
                .formLogin(
                        login ->
                                login.loginPage("/auth/login")
                                        .defaultSuccessUrl("/auth/login-success")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                )
                .logout(
                        logout ->
                                logout.logoutUrl("/auth/logout")
                                        .logoutSuccessUrl("/auth/login")
                )
                .authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }
}

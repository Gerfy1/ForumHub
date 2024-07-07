package com.forum.project.ForumProject.infra.security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter secFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req ->{
                            req.requestMatchers("/login").permitAll();
                            req.requestMatchers("/topicos/**").permitAll();
                            req.requestMatchers("/v3/api-docs/**","/swagger-ui.html", "/swagger-ui/**").permitAll();
                            req.requestMatchers("/usuarios/**").permitAll();
                            req.anyRequest().authenticated();
                        })
                        .addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Forum HUB")
                        .description("API Rest do FORUM HUB, contendo as funcionalidades de CRUD de usuarios, perfil, topico, respostas e validações")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@forum.hub"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forum.hub/api/licenca")));
    }
}

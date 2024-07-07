package com.forum.project.ForumProject.infra.security;


import com.forum.project.ForumProject.domain.autenticacaoDeUsuario.AutenticacaoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacaoRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (request.getRequestURI().endsWith("/usuarios") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var autenticacaoDeUsuario = repository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(autenticacaoDeUsuario, null, autenticacaoDeUsuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
       var authorizationHeader = request.getHeader("Authorization");

       if (authorizationHeader != null){
           return authorizationHeader.replace("Bearer ", "".trim());
       }
       return null;
    }

}

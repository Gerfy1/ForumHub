package com.forum.project.ForumProject.infra.security;

import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    private String gerarToken ()

}

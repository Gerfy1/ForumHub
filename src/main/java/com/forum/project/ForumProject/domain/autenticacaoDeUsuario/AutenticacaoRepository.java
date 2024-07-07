package com.forum.project.ForumProject.domain.autenticacaoDeUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AutenticacaoRepository extends JpaRepository<AutenticacaoDeUsuario, Long> {
    UserDetails findByLogin(String login);
}

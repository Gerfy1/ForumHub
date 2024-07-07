package com.forum.project.ForumProject.domain.resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    Page<Resposta> findByTopicoId(Long topicoId, Pageable pageable);
}

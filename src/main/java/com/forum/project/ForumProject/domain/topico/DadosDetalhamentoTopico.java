package com.forum.project.ForumProject.domain.topico;

import com.forum.project.ForumProject.domain.resposta.Resposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data, String autor, String status, List<Resposta> respostas) {
    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getAutor(), topico.getStatus(), topico.getRespostas());
    }
}

package com.forum.project.ForumProject.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(Long id, String mensagem, LocalDateTime data, Boolean resolucao) {
    public DadosListagemResposta (Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getData(), resposta.getResolucao());
    }
}

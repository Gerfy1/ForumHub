package com.forum.project.ForumProject.domain.resposta;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DadosDetalhamentoResposta {
    private Long id;
    private String mensagem;
    private LocalDateTime data;
    private Boolean resolucao;

    public DadosDetalhamentoResposta(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.data = resposta.getData();
        this.resolucao = resposta.getResolucao();
    }
}

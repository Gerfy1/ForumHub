package com.forum.project.ForumProject.domain.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoResposta(

        @NotBlank String mensagem
) {
}

package com.forum.project.ForumProject.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico (
        @NotNull String titulo,
        @NotNull String mensagem
) {
}

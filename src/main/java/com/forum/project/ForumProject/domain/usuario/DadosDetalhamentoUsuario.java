package com.forum.project.ForumProject.domain.usuario;

public record DadosDetalhamentoUsuario (Long id, String nome, String email) {

    public DadosDetalhamentoUsuario(Usuario user) {
        this(user.getId(), user.getNome(), user.getEmail());
    }

}

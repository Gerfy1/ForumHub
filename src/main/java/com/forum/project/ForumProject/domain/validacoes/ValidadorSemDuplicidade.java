package com.forum.project.ForumProject.domain.validacoes;

import com.forum.project.ForumProject.domain.topico.TopicoRepository;
import com.forum.project.ForumProject.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorSemDuplicidade {
    private final TopicoRepository topicoRepository;

    @Autowired
    public ValidadorSemDuplicidade(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public void validarDuplicidade(String titulo, String mensagem) throws ValidacaoException {
        if(topicoRepository.existsByTituloAndMensagem(titulo, mensagem)){
            throw new ValidacaoException("Existe um tópico com esse mesmo título & mensagem!");
        }
    }
}

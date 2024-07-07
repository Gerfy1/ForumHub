package com.forum.project.ForumProject.domain.validacoes;

import com.forum.project.ForumProject.infra.exception.ValidacaoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorId {
    public <T, ID> void validar (ID id, JpaRepository<T, ID> repository) throws ValidacaoException {
        if (id == null || (id instanceof Long && (Long) id <=0)){
            throw new ValidacaoException("Id inválido! informe um ID valido!");
        }
        repository.findById(id).orElseThrow(() -> new ValidacaoException("ID não localizado: " +id));
    }
}

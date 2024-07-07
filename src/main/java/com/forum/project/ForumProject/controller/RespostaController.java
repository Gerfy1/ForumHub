package com.forum.project.ForumProject.controller;

import com.forum.project.ForumProject.domain.resposta.*;
import com.forum.project.ForumProject.domain.topico.TopicoRepository;
import com.forum.project.ForumProject.domain.validacoes.ValidadorId;
import com.forum.project.ForumProject.domain.validacoes.ValidadorSemDuplicidade;
import com.forum.project.ForumProject.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos/{id}/respostas")
public class RespostaController {

    @Autowired
    private TopicoRepository topoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private ValidadorSemDuplicidade validadorSemDuplicidade;

    @Autowired
    private ValidadorId validadorId;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar (@PathVariable Long id,
                                        @RequestBody @Valid DadosCadastroResposta dados,
                                        UriComponentsBuilder uriBuilder,
                                        Authentication autenticado) throws ValidacaoException {
        validadorId.validar(id, topoRepository);

        var topico = topoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não localizado!"));

        var resposta = new Resposta(dados, autenticado.getName(), topico);
        respostaRepository.save(resposta);

        var uri = uriBuilder.path("/topicos/{id}/respostas/{idResposta}")
                .buildAndExpand(id, resposta.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping("/{id2}")
    public ResponseEntity detalhar (@PathVariable Long id2) throws ValidacaoException {
        validadorId.validar(id2, topoRepository);
        var resposta = respostaRepository.findById(id2).orElseThrow(()-> new ValidationException("Id não encontrado!"));
        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @PutMapping("/{id2}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id2, @RequestBody @Valid DadosAtualizacaoResposta dados, Authentication authentication) throws ValidacaoException{
        validadorId.validar(id2, respostaRepository);
        var resposta = respostaRepository.getReferenceById(id2);

        if (!resposta.getAutor().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        resposta.atualizarAsInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @DeleteMapping("/{id2}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id2, Authentication authentication) throws ValidacaoException{
        validadorId.validar(id2, respostaRepository);
        var resposta = respostaRepository.findById(id2).orElseThrow(()-> new ValidationException("Resposta não localizada para o id:" +id2));

        if (resposta == null || !resposta.getAutor().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        respostaRepository.deleteById(id2);
        return ResponseEntity.noContent().build();
    }

}

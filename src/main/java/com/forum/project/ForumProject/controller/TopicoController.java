package com.forum.project.ForumProject.controller;


import com.forum.project.ForumProject.domain.topico.*;
import com.forum.project.ForumProject.domain.validacoes.ValidadorId;
import com.forum.project.ForumProject.domain.validacoes.ValidadorSemDuplicidade;
import com.forum.project.ForumProject.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ValidadorSemDuplicidade validadorSemDuplicidade;

    @Autowired
    private ValidadorId validadorId;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder, Authentication autenticado) throws ValidacaoException {
        validadorSemDuplicidade.validarDuplicidade(dados.titulo(), dados.mensagem());
        var topico = new Topico(dados, autenticado.getName());
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar (@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id) throws ValidacaoException {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não localizado!"));
        topico.getRespostas().size();
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar (@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados, Authentication authentication) throws ValidacaoException {
        validadorId.validar(id, topicoRepository);
        var topico = topicoRepository.getOne(id);

        if(!topico.getAutor().equals(authentication.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id, Authentication authentication) throws ValidacaoException {
        validadorId.validar(id, topicoRepository);
        var topico = topicoRepository.findById(id).orElse(null);

        if (topico == null || !topico.getAutor().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

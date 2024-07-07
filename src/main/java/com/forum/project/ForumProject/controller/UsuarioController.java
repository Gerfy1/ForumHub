package com.forum.project.ForumProject.controller;

import com.forum.project.ForumProject.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uri){
        var usuario = new Usuario(dadosCadastroUsuario);
        repository.save(usuario);
        var urii = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId());
        return ResponseEntity.created(urii.toUri()).body(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarUsuario dados, Authentication auth){
        var usuario = repository.getReferenceById(dados.id());
        if (usuario == null || usuario.getId() == null || !usuario.getEmail().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        usuario.atualizarDadosUsuario(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id, Authentication auth){
        var usuario = repository.getReferenceById(id);

        if (usuario == null || usuario.getId() == null || !usuario.getEmail().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        usuario.excluirDadosUsuario();
        return ResponseEntity.noContent().build();
    }

}

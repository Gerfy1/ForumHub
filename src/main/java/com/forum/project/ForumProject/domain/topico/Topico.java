package com.forum.project.ForumProject.domain.topico;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.forum.project.ForumProject.domain.resposta.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private String status;
    private LocalDateTime data;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resposta> respostas = new ArrayList<>();

    public Topico (DadosCadastroTopico dados, String usuarioLogado){
        this.mensagem = dados.mensagem();
        this.titulo = dados.titulo();
        this.data = LocalDateTime.now();
        this.autor = usuarioLogado;
        this.status = "NAO_RESPONDIDO";
        this.curso = dados.curso();
    }

    public void atualizarInformacoes (DadosAtualizacaoTopico dados){
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }


}

package com.forum.project.ForumProject.domain.resposta;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.forum.project.ForumProject.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private Boolean resolucao;
    private LocalDateTime data;


    @ManyToOne
    @JoinColumn(name = "topico_id")
    @JsonBackReference
    private Topico topico;

    private String autor;

    public Resposta(DadosCadastroResposta dados, String autor, Topico topico){
        this.autor = autor;
        this.data = LocalDateTime.now();
        this.mensagem = dados.getMensagem();
        this.resolucao = false;
        this.topico = topico;
    }

    public void atualizarAsInformacoes(DadosAtualizacaoResposta dados){
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }
}

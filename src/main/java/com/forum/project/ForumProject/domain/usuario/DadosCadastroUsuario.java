package com.forum.project.ForumProject.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(

        @NotBlank String nome,

        @NotBlank
        @Email String email,

        @Pattern(
                regexp = "[A-Za-z\\d]${8}",
                message = "É necessario que a senha possua pelo menos 8 caracteres (Letras & numeros)!"
        )
        String senha) {
}

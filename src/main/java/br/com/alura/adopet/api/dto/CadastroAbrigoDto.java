package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record CadastroAbrigoDto(
        @NotNull Long idPet,
        String nome,
        String telefone,
        String email
) {
}

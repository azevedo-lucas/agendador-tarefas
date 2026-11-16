package com.agendador.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TarefaRequestDTO {

    @NotBlank(message = "O título não pode estar em branco")
    private String titulo;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "A data e hora são obrigatórias")
    private LocalDateTime dataHora; // Formato: "yyyy-MM-dd'T'HH:mm:ss

}

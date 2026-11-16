package com.agendador.tarefas.dto;

import com.agendador.tarefas.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataHora; // Formato: "yyyy-MM-dd'T'HH:mm:ss"

    private StatusTarefa status;

    private Long usuarioId;
}

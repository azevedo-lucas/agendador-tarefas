package com.agendador.tarefas.repository;

import com.agendador.tarefas.enums.StatusTarefa;
import com.agendador.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuarioId(Long usuarioId);

    List<Tarefa> findByStatus(StatusTarefa status);

    List<Tarefa> findByStatusAndUsuarioId(StatusTarefa status, Long usuarioId);
}

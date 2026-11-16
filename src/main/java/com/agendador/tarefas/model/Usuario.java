package com.agendador.tarefas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    private boolean ativo = true;

    /*
     * Método para preencher a data de cadastro antes de persistir o usuário no banco de dados.
     *Este método preenche automaticamente a data e hora do cadastro do usuário antes de sua
     *primeira gravação no banco de dados. O @PrePersist faz o JPA executar o método automaticamente,
     *enquanto o if verifica se a data está vazia (null). Se estiver, LocalDateTime.now() obtém a data e hora
     * atuais e as atribui ao atributo dataCadastro. O this representa o objeto Usuario atual, garantindo que
     * a data seja preenchida no usuário correto.
     */
    @PrePersist
    private void preencherDataCadastro() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }
}

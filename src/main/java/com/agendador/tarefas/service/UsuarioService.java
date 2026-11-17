package com.agendador.tarefas.service;

import com.agendador.tarefas.dto.UsuarioRequestDTO;
import com.agendador.tarefas.dto.UsuarioResponseDTO;
import com.agendador.tarefas.model.Usuario;
import com.agendador.tarefas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioSalvo.getNome(), usuarioSalvo.getEmail(),
                usuarioSalvo.isAtivo());
    }

    public UsuarioResponseDTO findById(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.isAtivo());

    }

    public UsuarioResponseDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.isAtivo());
    }

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(),
                        usuario.isAtivo()))
                .toList();
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent() &&
                !usuario.getEmail().equals(usuarioDTO.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioAtualizado.getNome(), usuarioAtualizado.getEmail(), usuarioAtualizado.isAtivo());
    }

    public UsuarioResponseDTO desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setAtivo(false);
        Usuario usuarioDesativado = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioDesativado.getNome(), usuarioDesativado.getEmail(), usuarioDesativado.isAtivo());
    }
}

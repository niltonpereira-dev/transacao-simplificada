package br.com.niltonpereira.transacao_simplificada.service;

import br.com.niltonpereira.transacao_simplificada.exceptions.UserNotFound;
import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Usuario;
import br.com.niltonpereira.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFound("Usuário não encontrado!"));
    }
}

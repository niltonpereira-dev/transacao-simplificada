package br.com.niltonpereira.transacao_simplificada.infrastructure.repository;

import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

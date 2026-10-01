package br.com.niltonpereira.transacao_simplificada.infrastructure.repository;

import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}

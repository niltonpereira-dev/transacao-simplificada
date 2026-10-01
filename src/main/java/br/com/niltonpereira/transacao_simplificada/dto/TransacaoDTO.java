package br.com.niltonpereira.transacao_simplificada.dto;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal value,
                           Long payer,
                           Long payee) {
}

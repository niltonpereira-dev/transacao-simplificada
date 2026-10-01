package br.com.niltonpereira.transacao_simplificada.infrastructure.clients;


import br.com.niltonpereira.transacao_simplificada.dto.AutorizacaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://util.devi.tools/api/v2/authorize", name = "autorizacao")
public interface AutorizacaoClient {

    @GetMapping
    AutorizacaoDTO validarAutorizacao();
}

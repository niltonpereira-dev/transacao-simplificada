package br.com.niltonpereira.transacao_simplificada.infrastructure.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://util.devi.tools/api/v1/notify", name = "notificacao")
public interface NotificacaoClient {

    @GetMapping
    void enviarNotificacao();
}

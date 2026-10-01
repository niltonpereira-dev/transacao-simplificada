package br.com.niltonpereira.transacao_simplificada.service;


import br.com.niltonpereira.transacao_simplificada.infrastructure.clients.NotificacaoClient;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private final NotificacaoClient notificacaoClient;

    public NotificacaoService(NotificacaoClient notificacaoClient) {
        this.notificacaoClient = notificacaoClient;
    }

    public void enviarNotificacao(){
        notificacaoClient.enviarNotificacao();
    }
}

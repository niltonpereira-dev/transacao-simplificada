package br.com.niltonpereira.transacao_simplificada.service;

import br.com.niltonpereira.transacao_simplificada.infrastructure.clients.AutorizacaoClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AutorizacaoService {

    private final AutorizacaoClient autorizacaoClient;


    public AutorizacaoService(AutorizacaoClient autorizacaoClient) {
        this.autorizacaoClient = autorizacaoClient;
    }

    public boolean validarTransferencia(){
        if(Objects.equals(autorizacaoClient.validarAutorizacao().data().autorization(), "true")){
            return true;
        }
        return false;
    }
}

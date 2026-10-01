package br.com.niltonpereira.transacao_simplificada.service;

import br.com.niltonpereira.transacao_simplificada.dto.TransacaoDTO;
import br.com.niltonpereira.transacao_simplificada.exceptions.BadRequestException;
import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Carteira;
import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.TipoUsuario;
import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Transacoes;
import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Usuario;
import br.com.niltonpereira.transacao_simplificada.infrastructure.repository.TransacoesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    private final UsuarioService usuarioService;
    private final AutorizacaoService autorizacaoService;
    private final CarteiraService carteiraService;
    private final TransacoesRepository transacoesRepository;
    private final NotificacaoService notificacaoService;

    public TransacaoService(UsuarioService usuarioService, AutorizacaoService autorizacaoService, CarteiraService carteiraService,
                            TransacoesRepository transacoesRepository, NotificacaoService notificacaoService) {
        this.usuarioService = usuarioService;
        this.autorizacaoService = autorizacaoService;
        this.carteiraService = carteiraService;
        this.transacoesRepository = transacoesRepository;
        this.notificacaoService = notificacaoService;
    }

    @Transactional
    public void transferirValores(TransacaoDTO transacaoDTO){
        Usuario pagador =  usuarioService.buscarUsuario(transacaoDTO.payer());
        Usuario recebedor = usuarioService.buscarUsuario(transacaoDTO.payee());

        validarPagadorLojista(pagador);
        validarSaldoUsuario(pagador, transacaoDTO.value());
        validarTransferencia();

        pagador.getCarteira().setSaldo(
                pagador.getCarteira().getSaldo().subtract(transacaoDTO.value())
        );
        atualizarCarteira(pagador.getCarteira());

        recebedor.getCarteira().setSaldo(
                pagador.getCarteira().getSaldo().add(transacaoDTO.value())
        );
        atualizarCarteira(recebedor.getCarteira());

        Transacoes transacoes = Transacoes.builder()
                .valor(transacaoDTO.value())
                .pagador(pagador)
                .recebedor(recebedor)
                .build();

        transacoesRepository.save(transacoes);
        enviarNotificacao();
    }

    private void validarPagadorLojista(Usuario usuario){
        try {
            if(usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)){
                throw new IllegalArgumentException("Transação não autorizada!");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validarSaldoUsuario(Usuario usuario, BigDecimal valor){
        try {
            if(usuario.getCarteira().getSaldo().compareTo(valor) < 0){
                throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    private void validarTransferencia(){
        try {
            if(!autorizacaoService.validarTransferencia()){
                throw new IllegalArgumentException("Transação não autorizada pela api");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void atualizarCarteira(Carteira carteira){
        carteiraService.salvar(carteira);
    }

    private void enviarNotificacao(){
        try {
            notificacaoService.enviarNotificacao();
        }catch (HttpClientErrorException e){
            throw new BadRequestException("Erro ao enviar notificação");
        }
    }
}

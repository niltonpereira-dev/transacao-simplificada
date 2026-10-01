package br.com.niltonpereira.transacao_simplificada.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transacao")
@Builder
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @JoinColumn(name = "recebedor_id")
    @ManyToOne
    private Usuario recebedor;

    @JoinColumn(name = "pagador_id")
    @ManyToOne
    private Usuario pagador;
    private LocalDateTime dataHoraTransacao;

    //Antes de inserir uma nova entidade no banco, o JPA executa esse método primeiro.
    @PrePersist
    void prePersist(){
        dataHoraTransacao = LocalDateTime.now();
    }

    public Transacoes(Long id, BigDecimal valor, Usuario recebedor, Usuario pagador, LocalDateTime dataHoraTransacao) {
        this.id = id;
        this.valor = valor;
        this.recebedor = recebedor;
        this.pagador = pagador;
        this.dataHoraTransacao = dataHoraTransacao;
    }

    public Transacoes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Usuario recebedor) {
        this.recebedor = recebedor;
    }

    public Usuario getPagador() {
        return pagador;
    }

    public void setPagador(Usuario pagador) {
        this.pagador = pagador;
    }

    public LocalDateTime getDataHoraTransacao() {
        return dataHoraTransacao;
    }

    public void setDataHoraTransacao(LocalDateTime dataHoraTransacao) {
        this.dataHoraTransacao = dataHoraTransacao;
    }


}

package br.com.niltonpereira.transacao_simplificada.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo;

    @JoinColumn(name = "usuario_id")
    @OneToOne
    private Usuario usuario;

    public Carteira(Long id, BigDecimal saldo, Usuario usuario) {
        this.id = id;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    public Carteira() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

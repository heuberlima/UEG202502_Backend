package com.br.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Integer quantidade;

    // 🔗 Muitos itens pertencem a um pedido
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @JsonBackReference // 🔹 evita recursão com Pedido
    private Pedido pedido;

    // 🔗 Muitos itens podem referenciar um automóvel
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Automovel getAutomovel() {
        return automovel;
    }
    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
}

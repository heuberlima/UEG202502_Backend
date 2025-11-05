package com.br.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private LocalDate dataPedido;
    private Double totalPedido;

    // 🔗 Relacionamento 1:N com ItensPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference // 🔹 evita recursão infinita
    private List<ItensPedido> itens;

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
    public Double getTotalPedido() {
        return totalPedido;
    }
    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }
    public List<ItensPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
        // Atualiza a referência do pedido em cada item
        if (itens != null) {
            itens.forEach(i -> i.setPedido(this));
        }
    }
}

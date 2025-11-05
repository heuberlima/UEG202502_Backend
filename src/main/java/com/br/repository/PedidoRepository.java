package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

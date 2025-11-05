package com.br.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.br.model.ItensPedido;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {
}

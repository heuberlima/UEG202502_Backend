package com.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.br.model.Pedido;
import com.br.repository.PedidoRepository;

@RestController
@RequestMapping("/cpedido")
@CrossOrigin(origins = "*") // permite chamadas de qualquer origem (útil para frontend)
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    //LISTAR TODOS OS PEDIDOS
    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    //CONSULTAR UM PEDIDO POR ID
    @GetMapping("/{id}")
    public Optional<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id);
    }

    //INCLUIR UM NOVO PEDIDO
    @PostMapping
    public Pedido incluir(@RequestBody Pedido pedido) {
        // O CascadeType.ALL no Pedido já salva os ItensPedido juntos
        return pedidoRepository.save(pedido);
    }

 // ALTERAR UM PEDIDO EXISTENTE
    @PutMapping("/{id}")
    public Pedido alterar(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id)
                .map(p -> {
                    // Atualiza os dados básicos
                    p.setDataPedido(pedidoAtualizado.getDataPedido());
                    p.setTotalPedido(pedidoAtualizado.getTotalPedido());

                    // Atualiza os itens corretamente (mantendo a lista original)
                    p.getItens().clear();
                    if (pedidoAtualizado.getItens() != null) {
                        for (var item : pedidoAtualizado.getItens()) {
                            // Garante que o relacionamento bidirecional permaneça coerente
                            item.setPedido(p);
                            p.getItens().add(item);
                        }
                    }

                    return pedidoRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
    }


    //EXCLUIR UM PEDIDO
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado para exclusão!");
        }
        pedidoRepository.deleteById(id);
    }
}

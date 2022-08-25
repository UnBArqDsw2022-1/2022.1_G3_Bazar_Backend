package com.fga.bazar.services;

import com.fga.bazar.models.Pedido;
import com.fga.bazar.models.enums.StatusPagamento;
import com.fga.bazar.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Pedido realizarPedido(Pedido pedido) {
        var cliente = usuarioRepository.findById(pedido.getCliente().getId()).orElseThrow();
        var enderecoEntrega = enderecoRepository.findById(pedido.getEnderecoEntrega().getId()).orElseThrow();

        pedido.setId(null);
        pedido.setData(Instant.now());
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(enderecoEntrega);
        pedido.getPagamento().setStatusPagamento(StatusPagamento.PEDENTE);
        pedido.getPagamento().setPedido(pedido);

        System.out.println(pedido.toString());

        pedido = pedidoRepository.save(pedido);

        pagamentoRepository.save(pedido.getPagamento());

        for (var item : pedido.getItens()) {
            var produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();

            item.setDesconto(0.0f);
            item.setProduto(produto);
            item.setPrecoItem(item.getProduto().getPreco());
            item.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }

}

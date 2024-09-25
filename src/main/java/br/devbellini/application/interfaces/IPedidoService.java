package br.devbellini.application.interfaces;

import br.devbellini.domain.model.Pedido;

import java.util.List;

public interface IPedidoService {
    void salvar(int numeroPedido);

    void atualizarPedido(int numeroPedido);

    void deletarPedido(int numeroPedido);

    List<Pedido> buscarTodosPedidos();

    Pedido buscarPorNumPedido(int numeroPedido);

}
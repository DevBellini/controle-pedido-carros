package br.devbellini.application.interfaces;

import br.devbellini.domain.model.Pedido;

import java.util.List;

public interface IPedidoService {
    void salvar(Pedido pedido); // Altera para receber um objeto Pedido
    void atualizarPedido(int numeroPedido);
    void deletarPedido(int numeroPedido);
    List<Pedido> buscarTodosPedidos();
    Pedido buscarPorNumPedido(int numeroPedido);
}

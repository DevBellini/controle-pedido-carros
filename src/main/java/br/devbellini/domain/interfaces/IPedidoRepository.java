package br.devbellini.domain.interfaces;

import br.devbellini.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepository {
    void salvar(int numeroPedido);
    void atualizarPedido(int numeroPedido);
    void deletar(int numeroPedido);
    List<Pedido> buscarTodosPedidos();
    Optional<Pedido> buscarPorNumPedido(int numeroPedido);

}

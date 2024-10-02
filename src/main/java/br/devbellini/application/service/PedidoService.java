package br.devbellini.application.service;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Pedido;
import br.devbellini.domain.repository.PedidoRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PedidoService implements IPedidoRepository {
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    @Override
    public void salvar(int numeroPedido) {
        if (pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + numeroPedido + " já cadastrado.");
        }
        Pedido novoPedido = new Pedido(numeroPedido);
        pedidos.put(numeroPedido, novoPedido);
    }

    @Override
    public void atualizarPedido(int numeroPedido) {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Pedido não cadastrado.");
        }
        Pedido pedidoAtualizado = new Pedido(numeroPedido);
        pedidos.put(numeroPedido, pedidoAtualizado);
    }

    @Override
    public void deletar(int numeroPedido) {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Pedido não cadastrado.");
        }
        pedidos.remove(numeroPedido);
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        return List.copyOf(pedidos.values());
    }

    @Override
    public Optional<Pedido> buscarPorNumPedido(int numeroPedido) {
        return Optional.ofNullable(pedidos.get(numeroPedido));
    }
}

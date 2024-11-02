package br.devbellini.application.service;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Pedido;
import br.devbellini.infra.exception.ExceptionResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PedidoService implements IPedidoRepository {
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        if (pedidos.containsKey(pedido.getNumeroPedido())) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + pedido.getNumeroPedido() + " já cadastrado.");
        }
        pedidos.put(pedido.getNumeroPedido(), pedido);
        // Aqui você pode também chamar o PedidoRepository para salvar no banco de dados
    }

    @Override
    public void atualizarPedido(int numeroPedido) {
        // Implementação para atualizar o pedido (se necessário)
    }

    @Override
    public void deletar(int numeroPedido) {
        // Implementação para deletar o pedido (se necessário)
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

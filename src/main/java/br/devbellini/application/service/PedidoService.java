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
    private final Map<Integer, Pedido> pedidos = new HashMap<>(); // Armazenamento em um mapa

    @Override
    public void salvar(int numeroPedido) {
        if (pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + numeroPedido + " já cadastrado.");
        }
        // Crie um novo pedido aqui com base no número, por exemplo:
        Pedido novoPedido = new Pedido(numeroPedido); // Supondo que o Pedido tenha um construtor que aceita o número do pedido
        pedidos.put(numeroPedido, novoPedido); // Adiciona o pedido ao mapa
    }

    @Override
    public void atualizarPedido(int numeroPedido) {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NÃO_CADASTRADO, "Pedido não cadastrado.");
        }
        // Para atualizar, você pode modificar diretamente o objeto que já está no mapa ou substituí-lo
        Pedido pedidoAtualizado = new Pedido(numeroPedido); // Crie o pedido atualizado conforme necessário
        pedidos.put(numeroPedido, pedidoAtualizado); // Atualiza o pedido no mapa
    }

    @Override
    public void deletar(int numeroPedido) {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NÃO_CADASTRADO, "Pedido não cadastrado.");
        }
        pedidos.remove(numeroPedido); // Remove o pedido do mapa
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        return List.copyOf(pedidos.values()); // Retorna todos os pedidos armazenados
    }

    @Override
    public Optional<Pedido> buscarPorNumPedido(int numeroPedido) {
        return Optional.ofNullable(pedidos.get(numeroPedido)); // Retorna o pedido correspondente ou vazio
    }
}

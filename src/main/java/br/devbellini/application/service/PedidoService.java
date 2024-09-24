package br.devbellini.application.service;

import br.devbellini.application.interfaces.IPedidoService;
import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Pedido;
import br.devbellini.infra.exception.ExceptionResponse;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PedidoService implements IPedidoService {

    private final IPedidoRepository _pedidoRepository;

    @Override
    public void salvar(int numeroPedido) {
        Optional<Pedido> pedidoOptional = _pedidoRepository.buscarPorNumPedido(numeroPedido);
        if (pedidoOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido já cadastrado");
        }
        _pedidoRepository.salvar(numeroPedido);
    }

    @Override
    public void atualizarPedido(int numeroPedido) {
        Optional<Pedido> pedidoOptional = _pedidoRepository.buscarPorNumPedido(numeroPedido);
        if (!pedidoOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NÃO_CADASTRADO, "Pedido não cadastrado");
        }
        _pedidoRepository.salvar(numeroPedido);
    }

    @Override
    public void deletarPedido(int numeroPedido) {
        Optional<Pedido> pedidoOptional = _pedidoRepository.buscarPorNumPedido(numeroPedido);
        if (!pedidoOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NÃO_CADASTRADO, "Pedido não cadastrado");
        }
        _pedidoRepository.deletar(numeroPedido);
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        return _pedidoRepository.buscarTodosPedidos();
    }

    @Override
    public Pedido buscarPorNumPedido(int numeroPedido) {
        Optional<Pedido> pedidoOptional = _pedidoRepository.buscarPorNumPedido(numeroPedido);
        if (!pedidoOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NÃO_CADASTRADO, "Pedido não cadastrado");
        }
        return pedidoOptional.get();
    }

}

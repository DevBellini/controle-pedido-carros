package br.devbellini.domain.repository;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Pedido;
import br.devbellini.infra.exception.ExceptionResponse;

import java.util.List;
import java.util.Optional;

public class PedidoRepository implements IPedidoRepository {
    @Override
    public void salvar(int numeroPedido) {
        if(buscarPorNumPedido(numeroPedido).isPresent()){
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + numeroPedido + " ja cadastrado.");
        }


    }

    @Override
    public void atualizarPedido(int numeroPedido) {

    }

    @Override
    public void deletar(int numeroPedido) {

    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        return List.of();
    }

    @Override
    public Optional<Pedido> buscarPorNumPedido(int numeroPedido) {
        return Optional.empty();
    }
}

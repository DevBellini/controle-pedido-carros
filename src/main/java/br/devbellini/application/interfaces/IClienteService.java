package br.devbellini.application.interfaces;

import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    void salvar(Cliente cliente);

    void atualizarCliente(Cliente cliente);

    void deletarCliente(String cnpj);

    List<Cliente> buscarTodosClientes();

    Optional<Cliente> buscarPorCnpj(String cnpj);
}

package br.devbellini.application.interfaces;

import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    void salvar(String cnpj);
    void atualizarCliente(String cnpj);
    void deletarCliente(String cnpj);
    List<Cliente> buscarTodosClientes();
    Optional<Cliente> buscarPorCnpj(String cnpj);

}

package br.devbellini.domain.interfaces;

import br.devbellini.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
    void salvar(String cnpj);
    void atualizarCliente(String cnpj);
    void deletarCliente(String cnpj);
    List<Cliente> buscarTodosClientes();
    Optional<Cliente> buscarPorCnpj(String cnpj);

}

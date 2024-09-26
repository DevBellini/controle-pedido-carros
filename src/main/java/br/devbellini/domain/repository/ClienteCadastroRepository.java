package br.devbellini.domain.repository;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteCadastroRepository implements IClienteRepository {
    @Override
    public void salvar(String cnpj) {

    }

    @Override
    public void atualizarCliente(String cnpj) {

    }

    @Override
    public void deletarCliente(String cnpj) {

    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        return List.of();
    }

    @Override
    public Optional<Cliente> buscarPorCnpj(String cnpj) {
        return Optional.empty();
    }
}

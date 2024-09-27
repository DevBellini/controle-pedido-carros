package br.devbellini.application.service;

import br.devbellini.application.interfaces.IClienteService;
import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.infra.exception.ExceptionResponse;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ClienteService implements IClienteService {

    private final IClienteRepository _clienteRepository;

    @Override
    public void salvar(Cliente cliente) {
        Optional<Cliente> clienteOptional = _clienteRepository.buscarPorCnpj(cliente.getCnpj());

        if (clienteOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_JA_CADASTRADO, "Cliente já cadastrado");
        } else {
            _clienteRepository.salvar(cliente);
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = _clienteRepository.buscarPorCnpj(cliente.getCnpj());
        if (!clienteOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_NÃO_CADASTRADO, "Cliente não cadastrado");
        }
        _clienteRepository.atualizarCliente(cliente);
    }

    @Override
    public void deletarCliente(String cnpj) {
        Optional<Cliente> clienteOptional = _clienteRepository.buscarPorCnpj(cnpj);
        if (!clienteOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_NÃO_CADASTRADO, "Cliente não cadastrado");
        }
        _clienteRepository.deletarCliente(cnpj);
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        return _clienteRepository.buscarTodosClientes();
    }

    @Override
    public Optional<Cliente> buscarPorCnpj(String cnpj) {
        Optional<Cliente> clienteOptional = _clienteRepository.buscarPorCnpj(cnpj);
        if (!clienteOptional.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_NÃO_CADASTRADO, "Cliente não cadastrado");
        }
        return clienteOptional;
    }
}

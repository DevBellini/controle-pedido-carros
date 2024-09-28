package br.devbellini.domain.repository;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.infra.exception.ExceptionResponse;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository implements IClienteRepository {

    @Override
    public void salvar(Cliente cliente) {
        // Verifica se o cliente já está cadastrado
        if (buscarPorCnpj(cliente.getCnpj()).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_JA_CADASTRADO, "Cliente já cadastrado.");
        }

        String sql = "INSERT INTO cliente(nome, cnpj, representante, telefone) VALUES (?, ?, ?, ?)";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCnpj());
            preparedStatement.setString(3, cliente.getRepresentante());
            preparedStatement.setString(4, cliente.getTelefone());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_JA_CADASTRADO, "Cliente já cadastrado.");
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        // Verifica se o cliente existe
        if (!buscarPorCnpj(cliente.getCnpj()).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_NÃO_CADASTRADO, "Cliente não cadastrado.");
        }

        String sql = "UPDATE cliente SET nome = ?, representante = ?, telefone = ? WHERE cnpj = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getRepresentante());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getCnpj());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_JA_CADASTRADO, "Cliente já cadastrado.");
        }
    }

    @Override
    public void deletarCliente(String cnpj) {
        // Verifica se o cliente existe
        if (!buscarPorCnpj(cnpj).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_NÃO_CADASTRADO, "Cliente não cadastrado.");
        }

        String sql = "DELETE FROM cliente WHERE cnpj = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, cnpj);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.ERRO_AO_DELETAR_CLIENTE, "Erro ao deletar cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente"; // Verifique se essa tabela existe
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCnpj(resultSet.getString("cnpj"));
                cliente.setRepresentante(resultSet.getString("representante"));
                cliente.setTelefone(resultSet.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Isso ajudará a identificar o problema
            throw new ExceptionResponse(ErrorCodes.ERRO_AO_BUSCAR_CLIENTES, "Erro ao buscar clientes: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM cliente WHERE TRIM(cnpj) = ?";
        Cliente clienteResult = null;
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, cnpj.trim());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    clienteResult = new Cliente();
                    clienteResult.setId_cliente(resultSet.getInt("id_cliente"));
                    clienteResult.setNome(resultSet.getString("nome"));
                    clienteResult.setCnpj(resultSet.getString("cnpj"));
                    clienteResult.setRepresentante(resultSet.getString("representante"));
                    clienteResult.setTelefone(resultSet.getString("telefone"));
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CLIENTE_JA_CADASTRADO, "Cliente ja cadastrado.");
        }
        return Optional.ofNullable(clienteResult);
    }
}

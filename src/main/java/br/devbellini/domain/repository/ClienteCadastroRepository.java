package br.devbellini.domain.repository;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class ClienteCadastroRepository implements IClienteRepository {

    @Override
    public void salvar(Cliente cliente) { // Alterado para aceitar um objeto Cliente
        String sql = "INSERT INTO cliente(nome, cnpj, representante, telefone) VALUES (?, ?, ?, ?)";
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null; // Usar PreparedStatement padrão

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome()); // Supondo que você tenha um método getNome() em Cliente
            preparedStatement.setString(2, cliente.getCnpj()); // Supondo que você tenha um método getCnpj() em Cliente
            preparedStatement.setString(3, cliente.getRepresentante()); // Supondo que você tenha um método getRepresentante() em Cliente
            preparedStatement.setString(4, cliente.getTelefone()); // Supondo que você tenha um método getTelefone() em Cliente

            // Executa o comando
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        } finally {
            // Fecha recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) { // Alterado para aceitar um objeto Cliente
        String sql = "UPDATE cliente SET nome = ?, representante = ?, telefone = ? WHERE cnpj = ?";
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null;

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getRepresentante());
            preparedStatement.setString(3, cliente.getTelefone());
            preparedStatement.setString(4, cliente.getCnpj());

            // Executa o comando
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        } finally {
            // Fecha recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
    }

    @Override
    public void deletarCliente(String cnpj) {
        String sql = "DELETE FROM cliente WHERE cnpj = ?";
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null;

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, cnpj);

            // Executa o comando
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        } finally {
            // Fecha recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
    }

    @Override
    public List<Cliente> buscarTodosClientes() {
        // Implementação da busca de todos os clientes (ainda a ser feita)
        return List.of();
    }

    @Override
    public Optional<Cliente> buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM cliente WHERE cnpj = ?"; // Corrigido para buscar pelo cnpj
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente clienteResult = null; // Inicializa como null

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, cnpj);

            // Executa a consulta
            resultSet = preparedStatement.executeQuery();

            // Se encontrar o cliente, cria o objeto e preenche os dados
            if (resultSet.next()) {
                clienteResult = new Cliente(); // Cria o objeto apenas se encontrar resultado
                clienteResult.setId_cliente(resultSet.getInt("id_cliente"));
                clienteResult.setNome(resultSet.getString("nome"));
                clienteResult.setCnpj(resultSet.getString("cnpj"));
                clienteResult.setRepresentante(resultSet.getString("representante"));
                clienteResult.setTelefone(resultSet.getString("telefone"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        } finally {
            // Fecha recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
        return Optional.ofNullable(clienteResult); // Retorna null se não encontrar o cliente
    }
}

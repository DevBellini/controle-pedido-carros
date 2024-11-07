package br.devbellini.domain.repository;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Carro;
import br.devbellini.domain.model.Pedido;
import br.devbellini.domain.model.Cliente;
import br.devbellini.infra.exception.ExceptionResponse;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoRepository implements IPedidoRepository {

    public void salvar(Pedido pedido) {
        if (buscarPorNumPedido(pedido.getNumeroPedido()).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + pedido.getNumeroPedido() + " já cadastrado.");
        }

        String sqlPedido = "INSERT INTO pedido(numero_pedido, id_cliente, valor_total) VALUES (?, ?, ?)";
        String sqlPedidoCarroInserir = "INSERT INTO pedido_carro(id_pedido, id_carro, quantidade) VALUES (?, ?, ?)";
        String sqlPedidoCarroAtualizar = "UPDATE pedido_carro SET quantidade = quantidade + ? WHERE id_pedido = ? AND id_carro = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatementPedido = connection.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementPedidoCarroInserir = connection.prepareStatement(sqlPedidoCarroInserir);
             PreparedStatement preparedStatementPedidoCarroAtualizar = connection.prepareStatement(sqlPedidoCarroAtualizar)) {

            // Inserindo o pedido
            preparedStatementPedido.setInt(1, pedido.getNumeroPedido());
            preparedStatementPedido.setInt(2, pedido.getCliente().getId_cliente());
            preparedStatementPedido.setBigDecimal(3, pedido.getValorTotal());

            int affectedRows = preparedStatementPedido.executeUpdate();

            if (affectedRows == 0) {
                throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao salvar pedido.");
            }

            // Obtendo o ID do pedido recém-inserido
            try (ResultSet generatedKeys = preparedStatementPedido.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPedido = generatedKeys.getInt(1); // id_pedido

                    // Inserindo ou atualizando os carros relacionados
                    for (Carro carro : pedido.getCarros()) {
                        // Verificar se o carro já existe no pedido
                        preparedStatementPedidoCarroAtualizar.setInt(1, 1); // A quantidade inicial
                        preparedStatementPedidoCarroAtualizar.setInt(2, idPedido);
                        preparedStatementPedidoCarroAtualizar.setInt(3, carro.getId_carro());

                        int rowsUpdated = preparedStatementPedidoCarroAtualizar.executeUpdate();
                        if (rowsUpdated == 0) {
                            // Se o carro não existe no pedido, insere ele com a quantidade 1
                            preparedStatementPedidoCarroInserir.setInt(1, idPedido);
                            preparedStatementPedidoCarroInserir.setInt(2, carro.getId_carro());
                            preparedStatementPedidoCarroInserir.setInt(3, 1); // A quantidade inicial
                            preparedStatementPedidoCarroInserir.executeUpdate();
                        }
                    }
                } else {
                    throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao obter ID do pedido.");
                }
            }

        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao salvar pedido: " + e.getMessage());
        }
    }




    @Override
    public void atualizarPedido(int numeroPedido) {
        // Implementação para atualizar o pedido (se necessário)
    }

    @Override
    public void deletar(int numeroPedido) {
        // Implementação para deletar o pedido (se necessário)
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = buscarClientePorId(resultSet.getInt("id_cliente")); // Método fictício
                List<Carro> carros = buscarCarrosPorPedido(resultSet.getInt("id_pedido")); // Buscar carros associados

                Pedido pedido = new Pedido(
                        resultSet.getInt("id_pedido"),
                        resultSet.getInt("numero_pedido"),
                        cliente,
                        resultSet.getBigDecimal("valor_total"),
                        carros // Passando a lista de carros
                );
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao buscar todos os pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    @Override
    public Optional<Pedido> buscarPorNumPedido(int numeroPedido) {
        String sql = "SELECT * FROM pedido WHERE numero_pedido = ?";
        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numeroPedido);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Cliente cliente = buscarClientePorId(resultSet.getInt("id_cliente")); // Método fictício
                    List<Carro> carros = buscarCarrosPorPedido(resultSet.getInt("id_pedido")); // Buscar carros associados

                    Pedido pedido = new Pedido(
                            resultSet.getInt("id_pedido"),
                            resultSet.getInt("numero_pedido"),
                            cliente,
                            resultSet.getBigDecimal("valor_total"),
                            carros // Passando a lista de carros
                    );
                    return Optional.of(pedido);
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao buscar pedido por número: " + e.getMessage());
        }
        return Optional.empty();
    }

    // Método para buscar carros associados a um pedido
    private List<Carro> buscarCarrosPorPedido(int idPedido) {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT c.* FROM carro c INNER JOIN pedido_carro pc ON c.id_carro = pc.id_carro WHERE pc.id_pedido = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idPedido);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Carro carro = new Carro();
                    carro.setId_carro(resultSet.getInt("id_carro"));
                    carro.setMarca(resultSet.getString("marca"));
                    carro.setModelo(resultSet.getString("modelo"));
                    carro.setAno(resultSet.getInt("ano"));
                    carro.setCor(resultSet.getString("cor"));
                    carro.setValor(resultSet.getFloat("valor"));
                    carros.add(carro);
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar carros do pedido: " + e.getMessage());
        }
        return carros;
    }

    // Método fictício para buscar cliente por ID
    private Cliente buscarClientePorId(int id_cliente) {
        // Lógica para buscar um cliente pelo ID no banco de dados
        // Isso pode ser feito através de um ClienteRepository que você já deve ter implementado
        return null; // Retorne um objeto Cliente apropriado após a implementação
    }
}

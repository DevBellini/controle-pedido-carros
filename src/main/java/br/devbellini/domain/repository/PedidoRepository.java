package br.devbellini.domain.repository;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Pedido;
import br.devbellini.infra.exception.ExceptionResponse;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoRepository implements IPedidoRepository {

    @Override
    public void salvar(int numeroPedido) {
        if (buscarPorNumPedido(numeroPedido).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_JA_CADASTRADO, "Pedido " + numeroPedido + " já cadastrado.");
        }

        String sql = "INSERT INTO pedido(numero_pedido) VALUES (?)";
        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numeroPedido);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao salvar pedido: " + e.getMessage());
        }
    }

    @Override
    public void atualizarPedido(int numeroPedido) {
        if (!buscarPorNumPedido(numeroPedido).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Pedido " + numeroPedido + " não encontrado.");
        }

        String sql = "UPDATE pedido SET numero_pedido = ? WHERE numero_pedido = ?";
        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numeroPedido);
            preparedStatement.setInt(2, numeroPedido); // ou outro campo para identificar o pedido
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int numeroPedido) {
        if (!buscarPorNumPedido(numeroPedido).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Pedido " + numeroPedido + " não encontrado.");
        }

        String sql = "DELETE FROM pedido WHERE numero_pedido = ?";
        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, numeroPedido);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao deletar pedido: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarTodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Supondo que o construtor de Pedido aceita o número do pedido e possivelmente outros atributos
                Pedido pedido = new Pedido(
                        resultSet.getInt("numero_pedido") // Adicione outros parâmetros conforme necessário
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
                    // Supondo que o construtor de Pedido aceita o número do pedido como parâmetro
                    Pedido pedido = new Pedido(resultSet.getInt("numero_pedido"));
                    return Optional.of(pedido);
                }
            }

        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.PEDIDO_NAO_CADASTRADO, "Erro ao buscar pedido por número: " + e.getMessage());
        }
        return Optional.empty();
    }
}

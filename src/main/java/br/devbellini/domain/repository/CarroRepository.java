package br.devbellini.domain.repository;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.model.Carro;
import br.devbellini.infra.exception.ExceptionResponse;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroRepository implements ICarroRepository {

    @Override
    public void salvar(Carro carro) {

        if (buscarPorModelo(carro.getModelo()).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_JA_CADASTRADO, "Carro já cadastrado.");
        }

        String sql = "INSERT INTO carro(marca, modelo, ano, cor, valor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, carro.getMarca());
            preparedStatement.setString(2, carro.getModelo());
            preparedStatement.setInt(3, carro.getAno());
            preparedStatement.setString(4, carro.getCor());
            preparedStatement.setFloat(5, carro.getValor());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao salvar carro: " + e.getMessage());
        }
    }

    @Override
    public void atualizarCarro(Carro carro) {

        if (!buscarPorId(carro.getId_carro()).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro não cadastrado.");
        }

        String sql = "UPDATE carro SET marca = ?, modelo = ?, ano = ?, cor = ?, valor = ? WHERE id_carro = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, carro.getMarca());
            preparedStatement.setString(2, carro.getModelo());
            preparedStatement.setInt(3, carro.getAno());
            preparedStatement.setString(4, carro.getCor());
            preparedStatement.setFloat(5, carro.getValor());
            preparedStatement.setInt(6, carro.getId_carro());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao atualizar carro: " + e.getMessage());
        }
    }

    @Override
    public void deletarCarro(Integer id) {

        if (!buscarPorId(id).isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro não cadastrado.");
        }

        String sql = "DELETE FROM carro WHERE id_carro = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao deletar carro: " + e.getMessage());
        }
    }

    @Override
    public List<Carro> buscarCarros(String marca, String modelo, int ano, String cor, float valor) {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carro WHERE marca = ? AND modelo = ? AND ano = ? AND cor = ? AND valor = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            preparedStatement.setInt(3, ano);
            preparedStatement.setString(4, cor);
            preparedStatement.setFloat(5, valor);

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
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar carros: " + e.getMessage());
        }
        return carros;
    }

    @Override
    public List<Carro> buscarTodosCarros() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carro";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar todos os carros: " + e.getMessage());
        }
        return carros;
    }

    @Override
    public Optional<Carro> buscarPorModelo(String modelo) {
        String sql = "SELECT * FROM carro WHERE TRIM(modelo) = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, modelo.trim());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Carro carro = new Carro();
                    carro.setId_carro(resultSet.getInt("id_carro"));
                    carro.setMarca(resultSet.getString("marca"));
                    carro.setModelo(resultSet.getString("modelo"));
                    carro.setAno(resultSet.getInt("ano"));
                    carro.setCor(resultSet.getString("cor"));
                    carro.setValor(resultSet.getFloat("valor"));
                    return Optional.of(carro);
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar carro por modelo: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Carro> buscarPorId(Integer id) {
        String sql = "SELECT * FROM carro WHERE id_carro = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Carro carro = new Carro();
                    carro.setId_carro(resultSet.getInt("id_carro"));
                    carro.setMarca(resultSet.getString("marca"));
                    carro.setModelo(resultSet.getString("modelo"));
                    carro.setAno(resultSet.getInt("ano"));
                    carro.setCor(resultSet.getString("cor"));
                    carro.setValor(resultSet.getFloat("valor"));
                    return Optional.of(carro);
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar carro por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Carro> buscarPorMarcaModeloEAno(String marca, String modelo, int ano) {
        String sql = "SELECT * FROM carro WHERE marca = ? AND modelo = ? AND ano = ?";
        try (Connection connectionMySQL = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connectionMySQL.prepareStatement(sql)) {

            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            preparedStatement.setInt(3, ano);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Carro carro = new Carro();
                    carro.setId_carro(resultSet.getInt("id_carro"));
                    carro.setMarca(resultSet.getString("marca"));
                    carro.setModelo(resultSet.getString("modelo"));
                    carro.setAno(resultSet.getInt("ano"));
                    carro.setCor(resultSet.getString("cor"));
                    carro.setValor(resultSet.getFloat("valor"));
                    return Optional.of(carro);
                }
            }
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Erro ao buscar carro por marca, modelo e ano: " + e.getMessage());
        }
        return Optional.empty();
    }
}

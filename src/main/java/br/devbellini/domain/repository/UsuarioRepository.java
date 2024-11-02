package br.devbellini.domain.repository;

import br.devbellini.domain.interfaces.IUsuarioRepository;
import br.devbellini.domain.model.Usuario;
import br.devbellini.infra.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioRepository implements IUsuarioRepository {

    @Override
    public void updateUsuario(Usuario usuario) {
        // Implementação do método para atualizar um usuário
    }

    @Override
    public void deleteUsuario(Integer id) {
        // Implementação do método para deletar um usuário
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuarioResult = null;

        try {
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, usuario);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioResult = new Usuario();
                usuarioResult.setId(resultSet.getInt("id_usuario"));
                usuarioResult.setNome(resultSet.getString("nome"));
                usuarioResult.setEmail(resultSet.getString("email"));
                usuarioResult.setUsuario(resultSet.getString("usuario"));
                usuarioResult.setSenha(resultSet.getString("senha"));
                usuarioResult.setTelefone(resultSet.getString("telefone"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        } finally {
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
        return usuarioResult;
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, usuario, senha, telefone) VALUES (?, ?, ?, ?, ?)";
        Connection connectionMySQL = null;
        PreparedStatement preparedStatement = null;

        try {
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();
            preparedStatement = connectionMySQL.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getUsuario());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setString(5, usuario.getTelefone());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
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
}

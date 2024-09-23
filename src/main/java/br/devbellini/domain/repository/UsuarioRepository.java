package br.devbellini.domain.repository;

import br.devbellini.domain.interfaces.IUsuarioRepository;
import br.devbellini.domain.model.Usuario;
import br.devbellini.infra.factory.ConnectionFactory;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;


public class UsuarioRepository implements IUsuarioRepository {

    @Override
    public void updateUsuario(Usuario usuario) {

    }

    @Override
    public void deleteUsuario(Integer id) {

    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM usuario where usuario = ? ";
        Connection connectionMySQL = null;
        JdbcPreparedStatement jdbcPreparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuarioResult = new Usuario();
        try {
            connectionMySQL = ConnectionFactory.createConncetionToMySQL();

            jdbcPreparedStatement = (JdbcPreparedStatement) connectionMySQL.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, String.valueOf(usuario));

            resultSet = jdbcPreparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioResult.setId(resultSet.getInt("id_usuario"));
                usuarioResult.setNome(resultSet.getString("nome"));
                usuarioResult.setEmail(resultSet.getString("email"));
                usuarioResult.setUsuario(resultSet.getString("usuario"));
                usuarioResult.setSenha(resultSet.getString("senha"));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (jdbcPreparedStatement != null) {
                    jdbcPreparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return usuarioResult;
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, usuario, senha) VALUES (?, ?, ?)";
        Connection connectionMySQL = null;
        JdbcPreparedStatement jdbcPreparedStatement = null;

        try {
            connectionMySQL = ConnectionFactory.createConncetionToMySQL();


            jdbcPreparedStatement = (JdbcPreparedStatement) connectionMySQL.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, usuario.getNome());
            jdbcPreparedStatement.setString(2, usuario.getEmail());
            jdbcPreparedStatement.setString(3, usuario.getUsuario());
            jdbcPreparedStatement.setString(4, usuario.getSenha());


            jdbcPreparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            try {
                if (jdbcPreparedStatement != null) {
                    jdbcPreparedStatement.close();
                }
                if (connectionMySQL != null) {
                    connectionMySQL.close();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
}





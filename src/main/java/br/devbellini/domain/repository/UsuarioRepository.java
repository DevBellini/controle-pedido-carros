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
        // Implementação omitida
    }

    @Override
    public void deleteUsuario(Integer id) {
        // Implementação omitida
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        Connection connectionMySQL = null;
        JdbcPreparedStatement jdbcPreparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuarioResult = null; // Inicializa como null

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            jdbcPreparedStatement = (JdbcPreparedStatement) connectionMySQL.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, usuario);

            // Executa a consulta
            resultSet = jdbcPreparedStatement.executeQuery();

            // Se encontrar o usuário, cria o objeto e preenche os dados
            if (resultSet.next()) {
                usuarioResult = new Usuario(); // Cria o objeto apenas se encontrar resultado
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
            // Fecha recursos
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
                throw new RuntimeException("Erro ao fechar recursos", e);
            }
        }
        return usuarioResult; // Retorna null se não encontrar o usuário
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email, usuario, senha, telefone) VALUES (?, ?, ?, ?, ?)";
        Connection connectionMySQL = null;
        JdbcPreparedStatement jdbcPreparedStatement = null;

        try {
            // Cria a conexão
            connectionMySQL = ConnectionFactory.createConnectionToMySQL();

            // Prepara o comando SQL
            jdbcPreparedStatement = (JdbcPreparedStatement) connectionMySQL.prepareStatement(sql);
            jdbcPreparedStatement.setString(1, usuario.getNome());
            jdbcPreparedStatement.setString(2, usuario.getEmail());
            jdbcPreparedStatement.setString(3, usuario.getUsuario());
            jdbcPreparedStatement.setString(4, usuario.getSenha());
            jdbcPreparedStatement.setString(5, usuario.getTelefone()); // Corrigido para usar o telefone

            // Executa o comando
            jdbcPreparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        } finally {
            // Fecha recursos
            try {
                if (jdbcPreparedStatement != null) {
                    jdbcPreparedStatement.close();
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

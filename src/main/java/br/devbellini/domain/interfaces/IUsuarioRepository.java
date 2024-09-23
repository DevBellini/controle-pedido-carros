package br.devbellini.domain.interfaces;

import br.devbellini.domain.model.Usuario;

public interface IUsuarioRepository {
    void save(Usuario usuario);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(Integer id);

    Usuario buscarPorUsuario(String usuario);

}

package br.devbellini.application.interfaces;

import br.devbellini.domain.model.Login;
import br.devbellini.domain.model.Usuario;

public interface IUsuarioService {
    void registerUser(Usuario usuario);
    Login login(Login login);
}

package br.devbellini.application.service;

import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.IUsuarioRepository;
import br.devbellini.application.interfaces.IUsuarioService;
import br.devbellini.domain.model.Login;
import br.devbellini.domain.model.Usuario;
import br.devbellini.infra.exception.ExceptionResponse;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class UsuarioService implements IUsuarioService {

    public final IUsuarioRepository _usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        _usuarioRepository = usuarioRepository;
    }

    @Override
    public void registerUser(Usuario usuario) {
        Usuario validarUsuario = _usuarioRepository.buscarPorUsuario(usuario.getUsuario());

        if (validarUsuario.getUsuario() != null) {
            throw new ExceptionResponse(ErrorCodes.USUARIO_JA_CADASTRADO, "Usuário já existe.");
        }

        String senha = usuario.getSenha();
        BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();
        String senhaCriptografada = basicPasswordEncryptor.encryptPassword(senha);

        usuario.setSenha(senhaCriptografada);
        _usuarioRepository.save(usuario);

    }

    @Override
    public Login login(Login login) {
        Usuario usuario = _usuarioRepository.buscarPorUsuario(login.getUsuario());
        BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();


        if (usuario.getUsuario() != null) {
            String senha = login.getSenha();
            String senhaCriptografada = usuario.getSenha();
            boolean senhaIgual = basicPasswordEncryptor.checkPassword(senha, senhaCriptografada);
            if (senhaIgual) {
                return login;
            } else {
                throw new ExceptionResponse(ErrorCodes.SENHA_INCORRETA, "Senha incorreta, tente novamente");
            }
        } else {
            throw new ExceptionResponse(ErrorCodes.USUARIO_NAO_CADASTRADO, "Usuário não encontrado cadastrado");
        }

    }


}

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
        // Busca o usuário pelo nome de usuário (login)
        Usuario validarUsuario = _usuarioRepository.buscarPorUsuario(usuario.getUsuario());

        // Verifica se o usuário já existe no banco de dados
        if (validarUsuario != null && validarUsuario.getUsuario() != null) {
            throw new ExceptionResponse(ErrorCodes.USUARIO_JA_CADASTRADO, "Usuário já existe.");
        }

        // Criptografa a senha do novo usuário
        String senha = usuario.getSenha();
        BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();
        String senhaCriptografada = basicPasswordEncryptor.encryptPassword(senha);

        // Define a senha criptografada no objeto usuário
        usuario.setSenha(senhaCriptografada);

        // Salva o novo usuário no banco de dados
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

package br.devbellini.view;

import br.devbellini.application.interfaces.IUsuarioService;
import br.devbellini.application.service.UsuarioService;
import br.devbellini.domain.repository.UsuarioRepository;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JDialog{

    private final IUsuarioService _usuarioService = new UsuarioService(new UsuarioRepository());

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton btnCancelar;
    private JButton btnOK;
    private JPanel JPanel;
    private JLabel senha;
    private JLabel usuario;
    private JPanel loginPanel;


    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }



    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }
}

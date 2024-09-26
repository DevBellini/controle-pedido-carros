package br.devbellini.view;

import br.devbellini.application.interfaces.IUsuarioService;
import br.devbellini.application.service.UsuarioService;
import br.devbellini.domain.model.Usuario;
import br.devbellini.domain.repository.UsuarioRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JDialog {

    private final IUsuarioService _usuarioService = new UsuarioService(new UsuarioRepository());
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoLogin;
    private JPasswordField campoConfirmarSenha;
    private JTextField campoTelefone;
    private JPasswordField campoSenha;
    private JPanel registroPanel;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JLabel repetirSenha;

    public Registro(JFrame parent) {
        super(parent);
        setTitle("Registro");
        setContentPane(registroPanel);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarUsuario();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);



    }

    private void RegistrarUsuario() {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String usuario = campoLogin.getText();
        String senha = String.valueOf(campoSenha.getPassword());
        String senhaConfirmada = String.valueOf(campoConfirmarSenha.getPassword());
        String telefone = campoTelefone.getText();
        if (nome.isEmpty() || email.isEmpty() || usuario.isEmpty()
                || senha.isEmpty() || senhaConfirmada.isEmpty()
                || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, entre com todos os campos\n" +
                            "Tente novamente");
            return;
        }
        if (!senha.equals(senhaConfirmada)) {
            JOptionPane.showMessageDialog(this,
                    "Senhas diferente \n" +
                            "Tente novamente!");
            return;
        }
        Usuario usuarioRegistrar = new Usuario();
        usuarioRegistrar.setNome(nome);
        usuarioRegistrar.setEmail(email);
        usuarioRegistrar.setUsuario(usuario);
        usuarioRegistrar.setSenha(senha);
        usuarioRegistrar.setTelefone(telefone);


        try {
            _usuarioService.registerUser(usuarioRegistrar);
        } catch (ExceptionResponse e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Registro feito com sucesso");
        dispose();
//        LoginForm loginForm = new LoginForm(null);

    }


//    public static void main(String[] args) {
//        Registro registro = new Registro(null);
//    }
}

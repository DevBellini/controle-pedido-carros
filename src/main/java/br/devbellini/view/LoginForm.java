package br.devbellini.view;

import br.devbellini.application.interfaces.IUsuarioService;
import br.devbellini.application.service.UsuarioService;
import br.devbellini.domain.model.Login;
import br.devbellini.domain.repository.UsuarioRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog {

    private final IUsuarioService _usuarioService = new UsuarioService(new UsuarioRepository());

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton btnRegistrar;
    private JButton btnEntrar;
    private JPanel JPanel;
    private JLabel senha;
    private JLabel usuario;
    private JPanel loginPanel;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Registro registroForm = new Registro(null);
            }
        });

        setVisible(true);
    }

    private void loginUser() {
        String usuario = campoUsuario.getText();
        String senha = String.valueOf(campoSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, entre com todos os campos\n" +
                            "Tente novamente");
            return;
        }

        Login login = new Login();
        login.setUsuario(usuario);
        login.setSenha(senha);

        try {
            _usuarioService.login(login);
        } catch (ExceptionResponse e) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Login feito com sucesso");

        dispose();
        // ShowtimeForm showtimeForm = new ShowtimeForm(null); // Caso queira chamar outra tela após login
    }

}

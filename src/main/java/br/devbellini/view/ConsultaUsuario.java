package br.devbellini.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaUsuario extends JDialog {
    private JLabel repetirSenha;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoLogin;
    private JPasswordField campoNovaSenha;
    private JTextField campoTelefone;
    private JPasswordField campoSenha;
    private JPasswordField campoRepSenha;
    private JButton btnEditar;
    private JButton btnSalvar;
    private JPanel consultaUsuario;
    private JButton btnVoltar;

    public ConsultaUsuario(JFrame parent) {
        super(parent);
        setTitle("Consulta Carro");
        setContentPane(consultaUsuario);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}

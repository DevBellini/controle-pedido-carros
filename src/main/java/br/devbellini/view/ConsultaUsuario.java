package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class ConsultaUsuario extends JDialog {
    private JLabel repetirSenha;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoLogin;
    private JPasswordField campoNovaSenha;
    private JTextField campoTelefone;
    private JPasswordField campoSenha;
    private JPasswordField campoRepSenha;
    private JButton btnEdit;
    private JButton btnSave;
    private JPanel consultaUsuario;

    public ConsultaUsuario(JFrame parent) {
        super(parent);
        setTitle("Consulta Carro");
        setContentPane(consultaUsuario);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ConsultaUsuario consultaUsuario = new ConsultaUsuario(null);
    }
}

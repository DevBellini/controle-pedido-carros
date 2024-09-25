package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class Registro extends JDialog {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JTextField campoTelefone;
    private JTextField campoLoginConfirma;
    private JPanel registroPanel;
    private JButton registrarButton;
    private JButton cancelarButton;

    public Registro(JFrame parent) {
        super(parent);
        setTitle("Registro");
        setContentPane(registroPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        Registro registro = new Registro(null);
    }
}

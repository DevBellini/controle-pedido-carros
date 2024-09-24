package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class CadastroCliente extends JDialog{
    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;
    private JPanel cadastroCliente;

    public CadastroCliente(JFrame parent) {
        super(parent);
        setTitle("CadastroCliente");
        setContentPane(cadastroCliente);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        CadastroCliente cadastroCliente = new CadastroCliente(null);
    }
}

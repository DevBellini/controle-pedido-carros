package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class ConsultaCliente extends JDialog{
    private JPanel consultaCliente;
    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;
    private JButton btnEditar;
    private JButton btnSalvar;

    public ConsultaCliente(JFrame parent) {
        super(parent);
        setTitle("ConsultaPedido");
        setContentPane(consultaCliente);
        setMinimumSize(new Dimension(600,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ConsultaCliente consultaCliente = new ConsultaCliente(null);
    }
}



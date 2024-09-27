package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class ConsultaCliente extends JDialog{
    private JTextField campoPesquisarCliente;
    private JButton pesquisarClienteButton;
    private JPanel consultaCliente;

    public ConsultaCliente(JFrame parent) {
        super(parent);
        setTitle("ConsultaPedido");
        setContentPane(consultaCliente);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ConsultaCliente consultaCliente = new ConsultaCliente(null);
    }
}



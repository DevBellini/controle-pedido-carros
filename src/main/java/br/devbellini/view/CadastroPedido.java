package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class CadastroPedido extends JDialog{
    private JTextField campoNumPedido;
    private JTextField campoCliente;
    private JTable table1;
    private JPanel cadastroPedido;
    private JButton salvarButton;

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("CadastroPedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        CadastroPedido cadastroPedido = new CadastroPedido(null);
    }
}

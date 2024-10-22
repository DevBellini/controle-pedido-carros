package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class ConsultaPedido extends  JDialog{
    private JTextField campoCliente;
    private JList itensPedido;
    private JButton btnEditar;
    private JTextField campoValor;
    private JButton btnPesquisar;
    private JLabel iconConsulta;
    private JPanel consultaPed;
    private JButton btnAdd;
    private JButton btnRmv;
    private JButton btnSalvar;
    private JLabel iconPedido;
    private JTextField numeroPedido;
    private JButton btnSave;

    public ConsultaPedido(JFrame parent) {
        super(parent);
        setTitle("Consulta de Pedido");
        setContentPane(consultaPed);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ConsultaPedido consultaPedido = new ConsultaPedido(null);
    }
}


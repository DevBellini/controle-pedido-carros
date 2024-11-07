package br.devbellini.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaPedido extends  JDialog{
    private JTextField campoCliente;
    private JList itensPedido;
    private JButton btnVoltar;
    private JTextField campoValor;
    private JButton btnPesquisar;
    private JLabel iconConsulta;
    private JPanel consultaPed;
    private JButton btnAdd;
    private JButton btnRmv;
    private JButton btnSalvar;
    private JLabel iconPedido;
    private JTextField numeroPedido;
    private JButton btnEditarPedido;
    private JTextField campoValorTotal;
    private JButton btnSave;

    public ConsultaPedido(JFrame parent) {
        super(parent);
        setTitle("Consulta de Pedido");
        setContentPane(consultaPed);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaPrincipal telaPrincipal = new TelaPrincipal(null);
                telaPrincipal.setVisible(true);
            }
        });

        setVisible(true);
    }
}


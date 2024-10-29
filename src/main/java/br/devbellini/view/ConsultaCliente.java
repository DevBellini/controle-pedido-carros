package br.devbellini.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaCliente extends JDialog {
    private JPanel consultaCliente;
    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;
    private JButton btnEditar;
    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnVoltar;

    public ConsultaCliente(JFrame parent) {
        super(parent);
        setTitle("ConsultaPedido");
        setContentPane(consultaCliente);
        setMinimumSize(new Dimension(600, 500));
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



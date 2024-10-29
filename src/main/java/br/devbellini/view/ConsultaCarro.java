package br.devbellini.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaCarro extends JDialog {
    private JPanel consultaCarro;
    private JTextField campoModelo;
    private JTextField campoMarca;
    private JTextField campoAno;
    private JTextField campoValor;
    private JTextField campoCor;
    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnVoltar;

    public ConsultaCarro(JFrame parent) {
        super(parent);
        setTitle("Consulta Carro");
        setContentPane(consultaCarro);
        setMinimumSize(new Dimension(600, 600));
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

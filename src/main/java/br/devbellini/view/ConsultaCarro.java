package br.devbellini.view;

import javax.swing.*;
import java.awt.*;

public class ConsultaCarro extends JDialog {
    private JPanel consultaCarro;
    private JTextField campoModelo;
    private JTextField campoMarca;
    private JTextField campoAno;
    private JTextField campoValor;
    private JTextField campoCor;
    private JButton btnSalvar;
    private JButton btnPesquisar;

    public ConsultaCarro(JFrame parent) {
        super(parent);
        setTitle("Consulta Carro");
        setContentPane(consultaCarro);
        setMinimumSize(new Dimension(600,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ConsultaCarro consultaCarro = new ConsultaCarro(null);
    }

}

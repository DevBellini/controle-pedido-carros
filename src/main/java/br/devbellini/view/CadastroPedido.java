package br.devbellini.view;

import br.devbellini.domain.model.Cliente;

import javax.swing.*;
import java.awt.*;

public class CadastroPedido extends JDialog {
    private JTextField campoNumPedido;
    private JTextField campoCliente;
    private JTable table1;
    private JPanel cadastroPedido;
    private JButton salvarButton;
    private JComboBox<Cliente> comboBoxClientes;
    private JList<String> list1; // Adicione esta linha

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("CadastroPedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializa o JList
        list1 = new JList<>(); // Inicializa o JList
        // Se você quiser adicionar o JList ao painel, faça isso aqui.
        // Exemplo:
        // cadastroPedido.add(list1);

        setVisible(true);
    }

    public static void main(String[] args) {
        CadastroPedido cadastroPedido = new CadastroPedido(null);
    }
}

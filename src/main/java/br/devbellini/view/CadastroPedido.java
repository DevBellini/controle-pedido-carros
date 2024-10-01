package br.devbellini.view;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.model.Carro;
import br.devbellini.domain.repository.ClienteRepository;
import br.devbellini.domain.repository.CarroRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroPedido extends JDialog {
    private JTextField campoNumPedido;
    private JPanel cadastroPedido;
    private JComboBox<Cliente> comboBoxClientes;
    private JTextField campoCarro;
    private JList<String> campoLista;
    private JButton btnAdd;
    private JButton btnRemove;
    private JComboBox<Carro> comboBoxCarros; // Alterado para Carro
    private DefaultListModel<String> listModel;

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("Cadastro de Pedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        campoLista.setModel(listModel);

        btnAdd.addActionListener(e -> adicionarCarro());
        btnRemove.addActionListener(e -> removerCarro());

        carregarClientes();
        carregarCarros(); // Carregar carros no ComboBox

        setVisible(true);
    }

    private void adicionarCarro() {
        String carro = campoCarro.getText();
        if (!carro.isEmpty()) {
            listModel.addElement(carro);
            campoCarro.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Insira um nome de carro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCarro() {
        int selectedIndex = campoLista.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um carro para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarClientes() {
        IClienteRepository clienteRepository = new ClienteRepository();
        try {
            List<Cliente> clientes = clienteRepository.buscarTodosClientes();

            for (Cliente cliente : clientes) {
                comboBoxClientes.addItem(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarCarros() {
        ICarroRepository carroRepository = new CarroRepository();
        try {
            List<Carro> carros = carroRepository.buscarTodos(); // Busca todos os carros

            for (Carro carro : carros) {
                comboBoxCarros.addItem(carro); // Adiciona cada carro ao ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar carros: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CadastroPedido cadastroPedido = new CadastroPedido(null);
    }
}

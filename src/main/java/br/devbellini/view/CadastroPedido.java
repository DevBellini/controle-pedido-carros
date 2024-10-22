package br.devbellini.view;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.model.Carro;
import br.devbellini.domain.model.Pedido;
import br.devbellini.domain.repository.ClienteRepository;
import br.devbellini.domain.repository.CarroRepository;
import br.devbellini.domain.repository.PedidoRepository;

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
    private JComboBox<Carro> comboBoxCarros;
    private JButton criarPedidoButton;
    private DefaultListModel<String> listModel;

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("Cadastro de Pedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(900, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        campoLista.setModel(listModel);

        btnAdd.addActionListener(e -> adicionarCarro());
        btnRemove.addActionListener(e -> removerCarro());

        criarPedidoButton.addActionListener(e -> criarPedido());

        carregarClientes();
        carregarCarros();

        setVisible(true);
    }

    private void adicionarCarro() {
        Carro carroSelecionado = (Carro) comboBoxCarros.getSelectedItem();
        if (carroSelecionado != null) {
            listModel.addElement(carroSelecionado.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um carro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
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
            List<Carro> carros = carroRepository.buscarTodosCarros();

            for (Carro carro : carros) {
                comboBoxCarros.addItem(carro);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar carros: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarPedido() {
        int numeroPedido;
        try {
            numeroPedido = Integer.parseInt(campoNumPedido.getText());
            Cliente clienteSelecionado = (Cliente) comboBoxClientes.getSelectedItem();

            if (clienteSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido pedido = new Pedido();
            pedido.setNumeroPedido(numeroPedido);
            pedido.setCliente(String.valueOf(clienteSelecionado));



            IPedidoRepository pedidoRepository = new PedidoRepository();
            JOptionPane.showMessageDialog(this, "Pedido criado com sucesso!");
            dispose(); // Fecha a janela após o sucesso

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número do pedido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        CadastroPedido cadastroPedido = new CadastroPedido(null);
//    }
}

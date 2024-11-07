package br.devbellini.view;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.interfaces.IPedidoRepository;
import br.devbellini.domain.model.Carro;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.model.Pedido;
import br.devbellini.domain.repository.ClienteRepository;
import br.devbellini.domain.repository.CarroRepository;
import br.devbellini.domain.repository.PedidoRepository;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CadastroPedido extends JDialog {
    private JTextField campoNumPedido;
    private JPanel cadastroPedido;
    private JComboBox<Cliente> comboBoxClientes;
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
        try {
            int numeroPedido = Integer.parseInt(campoNumPedido.getText());
            Cliente clienteSelecionado = (Cliente) comboBoxClientes.getSelectedItem();

            if (clienteSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido pedido = new Pedido();
            pedido.setNumeroPedido(numeroPedido);
            pedido.setCliente(clienteSelecionado);
            pedido.setValorTotal(calcularValorTotal()); // Certifique-se de calcular o valor total

            // Adicionar carros ao pedido
            List<Carro> carrosSelecionados = new ArrayList<>();
            Enumeration<String> elementos = listModel.elements();
            while (elementos.hasMoreElements()) {
                String carroStr = elementos.nextElement();
                Carro carro = buscarCarroPorString(carroStr);
                if (carro != null) {
                    carrosSelecionados.add(carro);
                }
            }
            pedido.setCarros(carrosSelecionados); // Definindo os carros no pedido

            IPedidoRepository pedidoRepository = new PedidoRepository();
            pedidoRepository.salvar(pedido); // Salva o pedido no banco de dados

            JOptionPane.showMessageDialog(this, "Pedido criado com sucesso!");

            // Fecha a tela de cadastro de pedido primeiro
            dispose(); // Fechar a tela de cadastro de pedido

            // Agora, abre a tela principal
            SwingUtilities.invokeLater(() -> {
                TelaPrincipal telaPrincipal = new TelaPrincipal(null); // A instância de TelaPrincipal
                telaPrincipal.setVisible(true); // Tornar visível a tela principal
            });

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número do pedido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir o erro completo
            JOptionPane.showMessageDialog(this, "Erro ao criar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private BigDecimal calcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;

        // Obtenha a Enumeration dos elementos do DefaultListModel
        Enumeration<String> elementos = listModel.elements();
        while (elementos.hasMoreElements()) {
            String carroStr = elementos.nextElement();
            Carro carro = buscarCarroPorString(carroStr);
            if (carro != null) {
                total = total.add(BigDecimal.valueOf(carro.getValor()));
            }
        }

        return total;
    }

    private Carro buscarCarroPorString(String carroStr) {
        ICarroRepository carroRepository = new CarroRepository();
        List<Carro> carros = carroRepository.buscarTodosCarros();

        for (Carro carro : carros) {
            if (carro.toString().equals(carroStr)) {
                return carro;
            }
        }
        return null;
    }
}

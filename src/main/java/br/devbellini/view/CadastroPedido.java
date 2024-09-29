package br.devbellini.view;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.repository.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroPedido extends JDialog {
    private JTextField campoNumPedido;
    private JPanel cadastroPedido;
    private JButton salvarButton;
    private JComboBox<Cliente> comboBoxClientes; // Combobox de clientes
    private JTextField campoCarro; // Campo para inserir o nome do carro
    private JList<String> campoLista; // Lista de carros adicionados
    private JButton btnAdd; // Botão para adicionar carro à lista
    private JButton btnRemove; // Botão para remover carro da lista
    private DefaultListModel<String> listModel; // Modelo da lista

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("Cadastro de Pedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializa o modelo da lista de carros
        listModel = new DefaultListModel<>();
        campoLista.setModel(listModel);

        // Configura os botões de adicionar e remover carro
        btnAdd.addActionListener(e -> adicionarCarro());
        btnRemove.addActionListener(e -> removerCarro());

        // Carrega os clientes no comboBox
        carregarClientes();

        setVisible(true);
    }

    private void adicionarCarro() {
        String carro = campoCarro.getText(); // Obtém o nome do carro inserido no textField1
        if (!carro.isEmpty()) {
            listModel.addElement(carro); // Adiciona o carro à lista
            campoCarro.setText(""); // Limpa o campo de entrada
        } else {
            JOptionPane.showMessageDialog(this, "Insira um nome de carro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCarro() {
        int selectedIndex = campoLista.getSelectedIndex(); // Obtém o índice do item selecionado
        if (selectedIndex != -1) { // Verifica se algum item está selecionado
            listModel.remove(selectedIndex); // Remove o item selecionado da lista
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um carro para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarClientes() {
        IClienteRepository clienteRepository = new ClienteRepository();
        try {
            // Buscando todos os clientes no repositório
            List<Cliente> clientes = clienteRepository.buscarTodosClientes();

            // Adicionando os clientes ao JComboBox
            for (Cliente cliente : clientes) {
                comboBoxClientes.addItem(cliente); // Adiciona o cliente ao comboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CadastroPedido cadastroPedido = new CadastroPedido(null);
    }
}

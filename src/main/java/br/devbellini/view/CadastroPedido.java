package br.devbellini.view;

import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.repository.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroPedido extends JDialog {
    private JTextField campoNumPedido;
    private JPanel cadastroPedido; // Este painel é o contêiner principal
    private JButton salvarButton;
    private JComboBox<Cliente> comboBoxClientes; // Combobox de clientes

    public CadastroPedido(JFrame parent) {
        super(parent);
        setTitle("Cadastro de Pedido");
        setContentPane(cadastroPedido);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Carrega os clientes no comboBox (já adicionado pelo GUI Designer)
        carregarClientes();

        setVisible(true);
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

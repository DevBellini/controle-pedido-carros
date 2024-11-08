package br.devbellini.view;

import br.devbellini.domain.model.Carro;
import br.devbellini.domain.model.Pedido;
import br.devbellini.domain.repository.PedidoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaPedido extends JDialog {
    private JTextField campoCliente;
    private JList<String> itensPedido;
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
    private JComboBox comboBoxCarros;
    private JButton btnSave;

    // Adicionando o modelo de lista para os itens do pedido
    private DefaultListModel<String> modeloLista;

    public ConsultaPedido(JFrame parent) {
        super(parent);
        setTitle("Consulta de Pedido");
        setContentPane(consultaPed);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializando o modelo da lista e associando ao JList
        modeloLista = new DefaultListModel<>();
        itensPedido.setModel(modeloLista);

        // Ação do botão Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaPrincipal telaPrincipal = new TelaPrincipal(null);
                telaPrincipal.setVisible(true);
            }
        });

        // Ação do botão Pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode pesquisar o pedido usando o número inserido no campo `numeroPedido`
                String numPedido = numeroPedido.getText();
                PedidoRepository pedidoRepository = new PedidoRepository();
                List<Pedido> pedidos = pedidoRepository.buscarTodosPedidos();

                // Limpando a lista antes de adicionar novos itens
                modeloLista.clear();

                // Filtrando os pedidos para encontrar o pedido com o número correspondente
                for (Pedido pedido : pedidos) {
                    if (String.valueOf(pedido.getNumeroPedido()).equals(numPedido)) {
                        campoValor.setText(pedido.getValorTotal().toString());

                        // Adicionando os itens do pedido à lista
                        for (Carro carro : pedido.getCarros()) {
                            modeloLista.addElement(carro.getMarca() + " " + carro.getModelo());
                        }
                    }
                }
            }
        });

        // Ação do botão Adicionar
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar um carro ao pedido (exemplo simples)
                JOptionPane.showMessageDialog(null, "Adicionar carro ao pedido");
            }
        });

        // Ação do botão Remover
        btnRmv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remover o carro selecionado da lista
                int selectedIndex = itensPedido.getSelectedIndex();
                if (selectedIndex != -1) {
                    modeloLista.remove(selectedIndex);
                    // Lógica adicional para remover o carro do banco de dados ou do pedido
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um carro para remover.");
                }
            }
        });

        // Ação do botão Salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salvar o pedido
                JOptionPane.showMessageDialog(null, "Salvar pedido");
            }
        });

        // Ação do botão Editar Pedido
        btnEditarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar o pedido
                JOptionPane.showMessageDialog(null, "Editar pedido");
            }
        });

        setVisible(true);
    }
}

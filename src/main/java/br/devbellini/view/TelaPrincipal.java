package br.devbellini.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JDialog {
    private JPanel telaPrincipal;
    private JLabel bemVindo;
    private JMenu menuUsuario, menuCarro, menuCliente, menuPedido;
    private JMenuBar menuBar;
    private JMenuItem cadastrarCliente, consultarCliente;
    private JMenuItem cadastrarPedido, consultarPedido;
    private JMenuItem editarUsuario;
    private JMenuItem cadastrarCarro, consultarCarro;

    public TelaPrincipal(JFrame parent) {
        super(parent);
        setTitle("Tela Home");
        setContentPane(telaPrincipal);
        setMinimumSize(new Dimension(700, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        menuBar = new JMenuBar();

        menuCliente = new JMenu("Cliente");
        cadastrarCliente = new JMenuItem("Cadastrar Cliente");
        consultarCliente = new JMenuItem("Consultar Cliente");
        menuCliente.add(cadastrarCliente);
        menuCliente.add(consultarCliente);

        menuPedido = new JMenu("Pedido");
        cadastrarPedido = new JMenuItem("Cadastrar Pedido");
        consultarPedido = new JMenuItem("Consultar Pedido");
        menuPedido.add(cadastrarPedido);
        menuPedido.add(consultarPedido);

        menuUsuario = new JMenu("Usuário");
        editarUsuario = new JMenuItem("Editar Usuário");
        menuUsuario.add(editarUsuario);

        menuCarro = new JMenu("Carro");
        cadastrarCarro = new JMenuItem("Cadastrar Carro");
        consultarCarro = new JMenuItem("Consultar Carro");
        menuCarro.add(cadastrarCarro);
        menuCarro.add(consultarCarro);

        menuBar.add(menuUsuario);
        menuBar.add(menuPedido);
        menuBar.add(menuCliente);
        menuBar.add(menuCarro);

        setJMenuBar(menuBar);


        cadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CadastroCliente cadastroCliente = new CadastroCliente(null);
            }
        });

        cadastrarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CadastroCarro cadastroCarro = new CadastroCarro(null);
            }
        });

        cadastrarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CadastroPedido cadastrarPedido = new CadastroPedido(null);
            }
        });

        consultarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ConsultaPedido consultaPedido = new ConsultaPedido(null);
            }
        });

        consultarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                 ConsultaCliente consultarCliente = new ConsultaCliente(null);
            }
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal(null);
    }
}
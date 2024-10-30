package br.devbellini.view;

import br.devbellini.application.interfaces.ICarroService;
import br.devbellini.application.interfaces.IClienteService;
import br.devbellini.application.service.CarroService;
import br.devbellini.application.service.ClienteService;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.interfaces.IClienteRepository;
import br.devbellini.domain.repository.CarroRepository;
import br.devbellini.domain.repository.ClienteRepository; // Ajuste conforme necessário

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JDialog {
    private JPanel telaPrincipal;
    private JLabel bemVindo;
    private JLabel carrinho;
    private JLabel pedido;
    private JButton btnEditar; // Declaração correta do JButton

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

        // Instanciar o repositório e serviço
        IClienteRepository clienteRepository = new ClienteRepository(); // Ajuste conforme sua implementação
        ICarroRepository carroRepository = new CarroRepository();
        IClienteService clienteService = new ClienteService(clienteRepository); // Criação do ClienteService
        ICarroService carroService = new CarroService(carroRepository);

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
                ConsultaCliente consultaCliente = new ConsultaCliente(null, clienteService); // Passa o clienteService
            }
        });

        consultarCarro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ConsultaCarro consultaCarro = new ConsultaCarro(null, carroService); // Passa o serviço de carro
            }
        });

        editarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ConsultaUsuario consultaUsuario = new ConsultaUsuario(null);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        TelaPrincipal telaPrincipal = new TelaPrincipal(null);
    }
}

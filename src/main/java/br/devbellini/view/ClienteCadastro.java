package br.devbellini.view;

import br.devbellini.application.interfaces.IClienteService;
import br.devbellini.application.service.ClienteService;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.repository.ClienteCadastroRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteCadastro extends JDialog {
    private final IClienteService _clienteService = new ClienteService(new ClienteCadastroRepository());
    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;
    private JPanel cadastroCliente;
    private JButton salvarButton;

    public ClienteCadastro(JFrame parent) {
        super(parent);
        setTitle("Cadastro de Cliente");
        setContentPane(cadastroCliente);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarCliente();
            }
        });

        setVisible(true);
    }

    private void RegistrarCliente() {
        String nome = campoEmpresa.getText();
        String cnpj = campoCNPJ.getText();
        String responsavel = campoResponsavel.getText();
        String telefone = campoTelefone.getText();

        if (nome.isEmpty() || cnpj.isEmpty() || responsavel.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, entre com todos os campos\n" +
                            "Tente novamente");
            return;
        }

        // Criação do objeto Cliente e preenchimento dos dados
        Cliente clienteCadastrar = new Cliente();
        clienteCadastrar.setNome(nome);
        clienteCadastrar.setCnpj(cnpj);
        clienteCadastrar.setRepresentante(responsavel);
        clienteCadastrar.setTelefone(telefone);

        try {
            _clienteService.salvar(clienteCadastrar);
        } catch (ExceptionResponse e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso");
        dispose();
    }

    // Para testar a classe
    public static void main(String[] args) {
        ClienteCadastro cadastroCliente = new ClienteCadastro(null);
    }
}

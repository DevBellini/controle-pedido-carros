package br.devbellini.view;

import br.devbellini.application.interfaces.IClienteService;
import br.devbellini.application.service.ClienteService;
import br.devbellini.domain.model.Cliente;
import br.devbellini.domain.repository.ClienteRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCliente extends JDialog {
    private final IClienteService _clienteService = new ClienteService(new ClienteRepository());
    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;
    private JPanel cadastroCliente;
    private JButton salvarButton;

    public CadastroCliente(JFrame parent) {
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
                 TelaPrincipal telaPrincipal = new TelaPrincipal(null);
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
                    "Por favor, preencha todos os campos.\n" +
                            "Tente novamente.");
            return;
        }

        if (!validarCNPJ(cnpj)) {
            JOptionPane.showMessageDialog(this,
                    "CNPJ inválido. Por favor, insira um CNPJ válido.");
            return;
        }

        Cliente clienteCadastrar = new Cliente();
        clienteCadastrar.setNome(nome);
        clienteCadastrar.setCnpj(cnpj);
        clienteCadastrar.setRepresentante(responsavel);
        clienteCadastrar.setTelefone(telefone);

        try {
            _clienteService.salvar(clienteCadastrar);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso");
            dispose();
        } catch (ExceptionResponse e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");
        if (cnpj.length() != 14) {
            return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        CadastroCliente cadastroCliente = new CadastroCliente(null);
//    }
}

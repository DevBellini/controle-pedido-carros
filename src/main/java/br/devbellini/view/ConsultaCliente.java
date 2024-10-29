package br.devbellini.view;

import br.devbellini.application.interfaces.IClienteService;
import br.devbellini.domain.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ConsultaCliente extends JDialog {
    private JPanel consultaCliente;

    private JTextField campoEmpresa;
    private JTextField campoCNPJ;
    private JTextField campoResponsavel;
    private JTextField campoTelefone;

    private JButton btnEditar;
    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnVoltar;

    private IClienteService clienteService; // Serviço para gerenciar clientes

    public ConsultaCliente(JFrame parent, IClienteService clienteService) {
        super(parent);
        setTitle("Consulta Cliente");
        setContentPane(consultaCliente);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.clienteService = clienteService; // Inicializa o serviço

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Habilita os campos para edição
                habilitarCampos(true);
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaPrincipal telaPrincipal = new TelaPrincipal(null);
                telaPrincipal.setVisible(true);
            }
        });

        setVisible(true);
    }

    private void habilitarCampos(boolean habilitar) {
        campoEmpresa.setEditable(habilitar);
        campoCNPJ.setEditable(true);
        campoResponsavel.setEditable(habilitar);
        campoTelefone.setEditable(habilitar);
    }

    private void salvarAlteracoes() {
        String cnpj = campoCNPJ.getText().trim();

        try {
            // Obtém as informações atualizadas dos campos
            String nome = campoEmpresa.getText().trim();
            String responsavel = campoResponsavel.getText().trim();
            String telefone = campoTelefone.getText().trim();

            // Tenta buscar o cliente existente antes de atualizar
            Optional<Cliente> clienteExistente = clienteService.buscarPorCnpj(cnpj);
            if (!clienteExistente.isPresent()) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado para atualização.");
                return; // Saia do método se o cliente não existir
            }

            // Atualiza os campos do cliente existente
            Cliente cliente = clienteExistente.get();
            cliente.setNome(nome);
            cliente.setRepresentante(responsavel);
            cliente.setTelefone(telefone);
            clienteService.atualizarCliente(cliente);

            // Exibe uma mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");

            // Fecha a tela de consulta e abre a tela principal
            dispose();
            TelaPrincipal telaPrincipal = new TelaPrincipal(null);
            telaPrincipal.setVisible(true);

            // Desabilita os campos após salvar
            habilitarCampos(false);
        } catch (Exception e) {
            // Tratamento de exceção genérica
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar as alterações: " + e.getMessage());
        }
    }

    private void pesquisar() {
        String cnpj = campoCNPJ.getText().trim();
        String empresa = campoEmpresa.getText().trim(); // O campo empresa não está sendo utilizado atualmente

        try {
            // Busca o cliente pelo CNPJ
            Optional<Cliente> clienteEncontrado = clienteService.buscarPorCnpj(cnpj);

            if (clienteEncontrado.isPresent()) {
                Cliente cliente = clienteEncontrado.get();
                // Preenche os campos com as informações do cliente encontrado
                campoEmpresa.setText(cliente.getNome());
                campoCNPJ.setText(cliente.getCnpj());
                campoResponsavel.setText(cliente.getRepresentante());
                campoTelefone.setText(cliente.getTelefone());

                // Habilita os campos para edição após a pesquisa
                habilitarCampos(false);
            } else {
                // Se não encontrou, avise o usuário
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com o CNPJ informado.");
            }
        } catch (Exception e) {
            // Tratamento de exceção genérica
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar o cliente: " + e.getMessage());
        }
    }
}

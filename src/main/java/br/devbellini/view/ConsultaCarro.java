package br.devbellini.view;

import br.devbellini.application.interfaces.ICarroService;
import br.devbellini.domain.model.Carro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ConsultaCarro extends JDialog {
    private JPanel consultaCarro;
    private JTextField campoModelo;
    private JTextField campoMarca;
    private JTextField campoAno;
    private JTextField campoValor;
    private JTextField campoCor;
    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnVoltar;
    private JButton btnEditar;

    private ICarroService carroService; // Serviço para gerenciar carros

    public ConsultaCarro(JFrame parent, ICarroService carroService) {
        super(parent);
        setTitle("Consulta Carro");
        setContentPane(consultaCarro);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.carroService = carroService; // Inicializa o serviço

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarCampos(true); // Habilita os campos para edição
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
        campoModelo.setEditable(habilitar);
        campoMarca.setEditable(habilitar);
        campoAno.setEditable(habilitar);
        campoValor.setEditable(habilitar);
        campoCor.setEditable(habilitar);
    }

    private void salvarAlteracoes() {
        String modelo = campoModelo.getText().trim();

        try {
            // Obtém as informações dos campos e converte ano e valor para os tipos corretos
            String marca = campoMarca.getText().trim();
            int ano = Integer.parseInt(campoAno.getText().trim()); // Conversão para int
            double valor = Double.parseDouble(campoValor.getText().trim()); // Conversão para double
            String cor = campoCor.getText().trim();

            // Busca o carro para atualizar
            Optional<Carro> carroExistente = carroService.buscarPorModelo(modelo);
            if (!carroExistente.isPresent()) {
                JOptionPane.showMessageDialog(this, "Carro não encontrado para atualização.");
                return;
            }

            // Atualiza os dados do carro
            Carro carro = carroExistente.get();
            carro.setMarca(marca);
            carro.setAno(ano); // Define o ano como int
            carro.setValor((float) valor); // Define o valor como double
            carro.setCor(cor);
            carroService.atualizarCarro(carro);

            JOptionPane.showMessageDialog(this, "Carro atualizado com sucesso!");

            // Fecha a tela de consulta e volta para a tela principal
            dispose();
            new TelaPrincipal(null).setVisible(true);

            habilitarCampos(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos para Ano e Valor.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar as alterações: " + e.getMessage());
        }
    }

    private void pesquisar() {
        String modelo = campoModelo.getText().trim();

        try {
            // Busca o carro pelo modelo
            Optional<Carro> carroEncontrado = carroService.buscarPorModelo(modelo);

            if (carroEncontrado.isPresent()) {
                Carro carro = carroEncontrado.get();

                // Preenche os campos com as informações do carro
                campoModelo.setText(carro.getModelo());
                campoMarca.setText(carro.getMarca());
                campoAno.setText(String.valueOf(carro.getAno())); // Exibe o ano como String
                campoValor.setText(String.valueOf(carro.getValor())); // Exibe o valor como String
                campoCor.setText(carro.getCor());

                habilitarCampos(false); // Desabilita os campos após a pesquisa
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum carro encontrado com o modelo informado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar o carro: " + e.getMessage());
        }
    }
}

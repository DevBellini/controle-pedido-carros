package br.devbellini.view;

import br.devbellini.application.interfaces.ICarroService;
import br.devbellini.application.service.CarroService;
import br.devbellini.domain.model.Carro;
import br.devbellini.domain.repository.CarroRepository;
import br.devbellini.infra.exception.ExceptionResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCarro extends JDialog {

    private final ICarroService _carroService = new CarroService(new CarroRepository());
    private JTextField campoMarca;
    private JTextField campoModelo;
    private JTextField campoAno;
    private JTextField campoCor;
    private JTextField campoValor;
    private JButton btnSalvar;
    private JPanel cadastroCarro;

    public CadastroCarro(JFrame parent) {
        super(parent);
        setTitle("Cadastro de carro");
        setContentPane(cadastroCarro);
        setMinimumSize(new Dimension(600, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarCarro();
            }
        });

        setVisible(true);
    }

    private void RegistrarCarro() {
        String marca = campoMarca.getText();
        String modelo = campoModelo.getText();
        String anoStr = campoAno.getText();
        String cor = campoCor.getText();
        String valorStr = campoValor.getText();

        if (marca.isEmpty() || modelo.isEmpty() || anoStr.isEmpty() || cor.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos\n" +
                            "Tente novamente.");
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Ano' deve ser um número válido.");
            return;
        }

        float valor;
        try {
            valor = Float.parseFloat(valorStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Valor' deve ser um número decimal válido.");
            return;
        }

        Carro carroRegistrar = new Carro();
        carroRegistrar.setMarca(marca);
        carroRegistrar.setModelo(modelo);
        carroRegistrar.setAno(ano);
        carroRegistrar.setCor(cor);
        carroRegistrar.setValor(valor);

        try {
            _carroService.salvar(carroRegistrar);
            JOptionPane.showMessageDialog(this, "Carro cadastrado com sucesso.");
            dispose(); // Fecha a janela após o registro com sucesso
        } catch (ExceptionResponse e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace(); // Log para depuração
        }
    }

    public static void main(String[] args) {
        CadastroCarro cadastroCarro = new CadastroCarro(null);
    }
}

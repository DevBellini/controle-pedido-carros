package br.devbellini.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Pedido {
    private Integer id_pedido;
    private int numeroPedido;
    private Cliente cliente; // Altere para ser um objeto Cliente
    private BigDecimal valorTotal; // Valor total do pedido
    private List<Carro> carros; // Adicionando uma lista de carros

    public Pedido(int id_pedido, int numeroPedido, Cliente cliente, BigDecimal valorTotal, List<Carro> carros) {
        this.id_pedido = id_pedido;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.carros = carros; // Inicializa a lista de carros
    }

    public Pedido(int numeroPedido, Cliente cliente, List<Carro> carros) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente; // Inicializa o cliente
        this.carros = carros; // Inicializa a lista de carros
    }

    public Pedido() {
        // Construtor padrão
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}

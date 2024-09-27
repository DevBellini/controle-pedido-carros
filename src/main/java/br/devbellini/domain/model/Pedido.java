package br.devbellini.domain.model;

import lombok.Data;

@Data
public class Pedido {

    private Integer id_pedido;
    private int numeroPedido;
    private String cliente;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

}

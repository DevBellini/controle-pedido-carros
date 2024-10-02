package br.devbellini.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Pedido {

    private Integer id_pedido;
    private int numeroPedido;
    private String cliente;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Pedido(int numeroPedido, Cliente clienteSelecionado, List<Carro> carros) {
    }

    public Pedido() {

    }
}

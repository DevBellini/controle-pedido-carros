package br.devbellini.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Integer id_pedido;
    private int numeroPedido;
    private String cliente;

}

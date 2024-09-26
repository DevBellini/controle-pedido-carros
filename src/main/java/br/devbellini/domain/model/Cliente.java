package br.devbellini.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer id_cliente;
    private String nome;
    private String cnpj;
    private String representante;
}

package br.devbellini.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer id_cliente;
    private String name;
    private String cnpj;
    private String representante;
}

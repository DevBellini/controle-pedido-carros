package br.devbellini.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Integer id_cliente;
    private String nome;
    private String cnpj;
    private String representante;
    private String telefone;

    public Cliente(String nome, String cnpj, String responsavel, String telefone) {
    }

    @Override
    public String toString() {
        return nome;
    }
}

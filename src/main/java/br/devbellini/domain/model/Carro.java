package br.devbellini.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {

    private Integer id;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private float valor;

    @Override
    public String toString() {
        return marca + " " + modelo;
    }

}

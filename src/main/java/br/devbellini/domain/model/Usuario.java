package br.devbellini.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String usuario;
    private String senha;

}

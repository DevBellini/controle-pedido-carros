package br.devbellini.domain.model;

import lombok.*;


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

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

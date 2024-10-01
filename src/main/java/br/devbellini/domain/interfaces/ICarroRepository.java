package br.devbellini.domain.interfaces;

import br.devbellini.domain.model.Carro;

import java.util.List;
import java.util.Optional;

public interface ICarroRepository {

    void salvar(Carro carro);

    void atualizarCarro(Carro carro);

    void deletarCarro(Integer id);

    List<Carro> buscarCarros(String marca, String modelo, int ano, String cor, float valor);


    List<Carro> buscarTodos();

    Optional<Carro> buscarPorModelo(String modelo);

    Optional<Carro> buscarPorId(Integer id);

    Optional<Carro> buscarPorMarcaModeloEAno(String marca, String modelo, int ano);
}

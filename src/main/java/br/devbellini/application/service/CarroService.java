package br.devbellini.application.service;

import br.devbellini.application.interfaces.ICarroService;
import br.devbellini.domain.enums.ErrorCodes;
import br.devbellini.domain.interfaces.ICarroRepository;
import br.devbellini.domain.model.Carro;
import br.devbellini.infra.exception.ExceptionResponse;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CarroService implements ICarroService {

    private final ICarroRepository _carroRepository;

    @Override
    public void salvar(Carro carro) {
        Optional<Carro> optionalCarro = _carroRepository.buscarPorMarcaModeloEAno(
                carro.getMarca(), carro.getModelo(), carro.getAno());

        if (optionalCarro.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_JA_CADASTRADO, "Carro já cadastrado com esses dados.");
        }
        _carroRepository.salvar(carro);
    }

    @Override
    public void atualizarCarro(Carro carro) {
        Optional<Carro> optionalCarro = _carroRepository.buscarPorId(carro.getId());

        if (!optionalCarro.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro não cadastrado.");
        }
        _carroRepository.atualizarCarro(carro);
    }

    @Override
    public void deletarCarro(Integer id) {
        Optional<Carro> optionalCarro = _carroRepository.buscarPorId(id);

        if (!optionalCarro.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro não cadastrado.");
        }
        _carroRepository.deletarCarro(id);
    }

    @Override
    public List<Carro> buscarCarros(String marca, String modelo, int ano, String cor, float valor) {
        List<Carro> carros = _carroRepository.buscarCarros(marca, modelo, ano, cor, valor);

        if (carros.isEmpty()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Nenhum carro encontrado com os filtros fornecidos.");
        }
        return carros;
    }

    @Override
    public List<Carro> buscarTodos() {
        List<Carro> carros = _carroRepository.buscarTodos();

        if (carros.isEmpty()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Nenhum carro encontrado.");
        }
        return carros;
    }

    @Override
    public Optional<Carro> buscarPorModelo(String modelo) {
        Optional<Carro> carro = _carroRepository.buscarPorModelo(modelo);

        if (!carro.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro com modelo " + modelo + " não encontrado.");
        }
        return carro;
    }

    @Override
    public Optional<Carro> buscarPorId(Integer id) {
        Optional<Carro> carro = _carroRepository.buscarPorId(id);

        if (!carro.isPresent()) {
            throw new ExceptionResponse(ErrorCodes.CARRO_NAO_CADASTRADO, "Carro com ID " + id + " não encontrado.");
        }
        return carro;
    }
}

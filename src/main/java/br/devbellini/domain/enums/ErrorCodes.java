package br.devbellini.domain.enums;

public enum ErrorCodes {
    SENHA_INCORRETA("Senha incorreta."),
    USUARIO_JA_CADASTRADO("Usuário ja cadastrado."),
    PEDIDO_JA_CADASTRADO("Pedido ja cadastrado."),
    PEDIDO_NAO_CADASTRADO("Pedido não cadastrado."),
    CLIENTE_JA_CADASTRADO("Cliente ja cadastrado."),
    CLIENTE_NAO_CADASTRADO("Cliente não cadastrado."),
    USUARIO_NAO_CADASTRADO("Usuário não cadastrado."),
    ERRO_AO_DELETAR_CLIENTE("Erro ao deletar cliente."),
    ERRO_AO_BUSCAR_CLIENTES("Erro ao buscar cliente"),
    CARRO_JA_CADASTRADO("Carro ja cadastrado"),
    CARRO_NAO_CADASTRADO("Carro não cadastrado"),
    GENERIC_ERROR("ERRO");



    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

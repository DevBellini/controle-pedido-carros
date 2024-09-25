package br.devbellini.domain.enums;

public enum ErrorCodes {
    SENHA_INCORRETA("Senha incorreta."),
    USUARIO_JA_CADASTRADO("Usuário ja cadastrado."),
    PEDIDO_JA_CADASTRADO("Pedido ja cadastrado."),
    PEDIDO_NÃO_CADASTRADO("Pedido não cadastrado"),
    CLIENTE_JA_CADASTRADO("Cliente ja cadastrado."),
    CLIENTE_NÃO_CADASTRADO("Cliente não cadastrado"),
    USUARIO_NAO_CADASTRADO("Usuário não cadastrado");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

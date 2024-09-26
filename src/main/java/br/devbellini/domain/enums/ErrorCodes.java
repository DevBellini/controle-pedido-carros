package br.devbellini.domain.enums;

public enum ErrorCodes {
    SENHA_INCORRETA("Senha incorreta."),
    USUARIO_JA_CADASTRADO("Usuário ja cadastrado."),
    PEDIDO_JA_CADASTRADO("Pedido ja cadastrado."),
    PEDIDO_NÃO_CADASTRADO("Pedido não cadastrado"),
    CLIENTE_JA_CADASTRADO("Cliente ja cadastrado."),
    CLIENTE_NÃO_CADASTRADO("Cliente não cadastrado"),
    USUARIO_NAO_CADASTRADO("Usuário não cadastrado"),
    ERRO_AO_SALVAR_CLIENTE("Erro ao salvar cliente"),
    ERRO_AO_ATUALIZAR_CLIENTE("Erro ao atualizar cliente"),
    ERRO_AO_DELETAR_CLIENTE("Erro ao deletar cliente"),
    ERRO_AO_BUSCAR_CLIENTES("Erro ao buscar cliente");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

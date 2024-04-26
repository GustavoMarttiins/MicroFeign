package com.br.Boleto.exception;

public class PessoaNotFoundException extends RuntimeException {

    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }
}

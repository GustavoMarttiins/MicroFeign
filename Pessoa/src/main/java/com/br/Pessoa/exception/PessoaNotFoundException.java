package com.br.Pessoa.exception;

public class PessoaNotFoundException extends RuntimeException{

    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }
}

package com.br.Pessoa.exception;

public class PessoaNotFoundException extends RuntimeException{

    public PessoaNotFoundException(String message) {
        super(message);
    }
}

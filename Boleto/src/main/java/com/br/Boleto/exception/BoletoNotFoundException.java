package com.br.Boleto.exception;

public class BoletoNotFoundException extends RuntimeException{
    public BoletoNotFoundException() {
        super("Boleto não encontrado");
    }

}

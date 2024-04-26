package com.br.Pessoa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate dataNascimento;
    private List<Boleto> boletos;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Boleto {
        private Long idBoleto;
        private BigDecimal valor;
        private BigDecimal valorPago;
        private LocalDate dataVencimento;
        private LocalDate dataPagamento;
        private String status;
    }
}

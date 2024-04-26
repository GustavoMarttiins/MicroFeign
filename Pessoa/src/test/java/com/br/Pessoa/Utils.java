package com.br.Pessoa;

import com.br.Pessoa.dto.BoletoDTO;
import com.br.Pessoa.entity.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Utils {

    public Pessoa getPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome");
        pessoa.setCpf("123.456.789-00");
        pessoa.setEndereco("Endereco");
        pessoa.setDataNascimento(LocalDate.of(2024, 1, 1));
        return pessoa;
    }

    public BoletoDTO getBoleto() {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setIdBoleto(1L);
        boletoDTO.setValor(new BigDecimal("100.00"));
        boletoDTO.setValorPago(new BigDecimal("100.00"));
        boletoDTO.setDataVencimento(LocalDate.of(2022, 12, 31));
        boletoDTO.setDataPagamento(LocalDate.of(2022, 12, 31));
        boletoDTO.setStatus("PAGO");
        return boletoDTO;
    }
}

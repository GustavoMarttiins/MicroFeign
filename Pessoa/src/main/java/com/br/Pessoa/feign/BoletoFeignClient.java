package com.br.Pessoa.feign;

import com.br.Pessoa.dto.BoletoDTO;
import com.br.Pessoa.entity.Pessoa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "boleto", url = "localhost:8000/")
public interface BoletoFeignClient {

    @GetMapping("/boletos/pessoa/{idPessoa}")
    List<BoletoDTO> getBoletosByPessoaId(@PathVariable("idPessoa") Long idPessoa);
}

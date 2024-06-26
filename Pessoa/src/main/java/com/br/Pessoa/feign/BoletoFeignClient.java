package com.br.Pessoa.feign;

import com.br.Pessoa.dto.BoletoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "boleto", url = "http://localhost:8000")
public interface BoletoFeignClient {

    @GetMapping("boleto/api/boletos/{id}")
    List<BoletoDTO> getBoletosByPessoaId(@PathVariable("id") Long id);
}
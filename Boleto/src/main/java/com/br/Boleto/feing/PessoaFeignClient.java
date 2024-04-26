package com.br.Boleto.feing;

import com.br.Boleto.dto.PessoaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pessoa", url = "localhost:8080")
public interface PessoaFeignClient {

    @GetMapping("/pessoa/api/{id}")
    PessoaDTO getPessoaById(@PathVariable("id") Long id);
}
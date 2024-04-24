package com.br.Pessoa.service;

import com.br.Pessoa.entity.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Pessoa createUsuario(Pessoa pessoa);

    Optional<Pessoa> updatePessoa(Long id, Pessoa updatedPessoa) throws JsonProcessingException;

    Optional<Pessoa> pessoaById(Long id);

    List<Pessoa> allPessoas();

    void deletePessoa(Long id) throws JsonProcessingException;
}

package com.br.Boleto.service;

import com.br.Boleto.entity.Boleto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface BoletoService {

    Boleto createBoleto(Boleto boleto);

    Optional<Boleto> updateBoleto(Long id, Boleto updatedBoleto) throws JsonProcessingException;

    Optional<Boleto> boletoById(Long id);

    List<Boleto> allBoleto();

    void deleteBoleto(Long id) throws JsonProcessingException;
}

package com.br.Boleto.repository;

import com.br.Boleto.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
    List<Boleto> findAllByIdPessoa(Long idPessoa);
}

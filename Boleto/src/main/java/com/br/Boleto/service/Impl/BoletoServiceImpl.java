package com.br.Boleto.service.Impl;

import com.br.Boleto.dto.BoletoDTO;
import com.br.Boleto.dto.PessoaDTO;
import com.br.Boleto.entity.Boleto;
import com.br.Boleto.enums.StatusBoleto;
import com.br.Boleto.exception.BoletoNotFoundException;
import com.br.Boleto.exception.PessoaNotFoundException;
import com.br.Boleto.feing.PessoaFeignClient;
import com.br.Boleto.repository.BoletoRepository;
import com.br.Boleto.service.BoletoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoletoServiceImpl implements BoletoService {

    private final BoletoRepository repository;
    private final ModelMapper modelMapper;
    private final PessoaFeignClient feignClient;

    public List<BoletoDTO> getBoletosByPessoaId(Long idPessoa) {
        List<Boleto> boletos = repository.findAllByIdPessoa(idPessoa);
        List<BoletoDTO> boletoDTO = new ArrayList<>();
        for (Boleto boleto : boletos) {
            boletoDTO.add(convertToDTO(boleto));
        }
        return boletoDTO;
    }

    private BoletoDTO convertToDTO(Boleto boleto) {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setIdBoleto(boleto.getIdBoleto());
        boletoDTO.setValor(boleto.getValor());
        boletoDTO.setValorPago(boleto.getValorPago());
        boletoDTO.setDataVencimento(boleto.getDataVencimento());
        boletoDTO.setDataPagamento(boleto.getDataPagamento());
        boletoDTO.setStatus(boleto.getStatus());
        return boletoDTO;
    }

    public void realizarPagamento(Long boletoId, BigDecimal valorPago, LocalDate dataPagamento) {
        Boleto boleto = repository.findById(boletoId)
                .orElseThrow(BoletoNotFoundException::new);
        boleto.setValorPago(valorPago);
        boleto.setDataPagamento(dataPagamento);
        boleto.setStatus(StatusBoleto.PAGO);
        repository.save(boleto);
    }

    public Boleto createBoleto(Boleto boleto) {
        PessoaDTO pessoaById = feignClient.getPessoaById(boleto.getIdPessoa());
        if (pessoaById == null) {
            throw new PessoaNotFoundException();
        }
        boleto.setStatus(StatusBoleto.PENDENTE);
        return repository.save(boleto);
    }

    public Optional<Boleto> boletoById(Long id) {
        return repository.findById(id);
    }

    public List<Boleto> allBoleto() {
        return repository.findAll();
    }

    public void deleteBoleto(Long id) {
        Boleto boletoExiste = getExistePessoa(id);
        repository.deleteById(id);
    }

    public Optional<Boleto> updateBoleto(Long id, Boleto updatedBoleto) {
        Boleto boletoExiste = getExistePessoa(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedBoleto, boletoExiste);
        return Optional.of(repository.save(boletoExiste));
    }

    private Boleto getExistePessoa(Long id) {
        Optional<Boleto> existeBoleto = this.boletoById(id);
        if (existeBoleto.isEmpty()) {
            throw new BoletoNotFoundException();
        }
        return existeBoleto.get();
    }
}

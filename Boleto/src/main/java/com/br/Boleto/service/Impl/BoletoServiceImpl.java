package com.br.Boleto.service.Impl;

import com.br.Boleto.dto.PessoaDTO;
import com.br.Boleto.entity.Boleto;
import com.br.Boleto.exception.BoletoNotFoundException;
import com.br.Boleto.feing.PessoaFeignClient;
import com.br.Boleto.repository.BoletoRepository;
import com.br.Boleto.service.BoletoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BoletoServiceImpl implements BoletoService {

    private final BoletoRepository repository;
    private final ModelMapper modelMapper;
    private final PessoaFeignClient feignClient;

    public PessoaDTO obterPessoaPorId(Long idPessoa) {
        return feignClient.getPessoaById(idPessoa);
    }

    public Boleto createBoleto(Boleto boleto) {
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
            throw new BoletoNotFoundException("Pessoa não encontrada com id: " + id);
        }
        return existeBoleto.get();
    }
}

package com.br.Pessoa.service.Impl;

import com.br.Pessoa.dto.BoletoDTO;
import com.br.Pessoa.entity.Pessoa;
import com.br.Pessoa.exception.PessoaNotFoundException;
import com.br.Pessoa.feign.BoletoFeignClient;
import com.br.Pessoa.repository.PessoaRepository;
import com.br.Pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;
    private final ModelMapper modelMapper;
    private final BoletoFeignClient boletoFeignClient;

    public List<BoletoDTO> obterBoletosPorIdPessoa(Long idPessoa) {
        return boletoFeignClient.getBoletosByPessoaId(idPessoa);
    }

    public Pessoa createUsuario(Pessoa pessoa) {
        return repository.save(pessoa);
    }
    public Optional<Pessoa> pessoaById(Long id) {
        return repository.findById(id);
    }

    public List<Pessoa> allPessoas() {
        return repository.findAll();
    }

    public void deletePessoa(Long id) {
        Pessoa pessoaExistente = getExistePessoa(id);
        repository.deleteById(id);
    }

    public Optional<Pessoa> updatePessoa(Long id, Pessoa updatedBoleto) {
        Pessoa pessoaExiste = getExistePessoa(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedBoleto, pessoaExiste);
        return Optional.of(repository.save(pessoaExiste));
    }

    private Pessoa getExistePessoa(Long id) {
        Optional<Pessoa> existePessoa = this.pessoaById(id);
        if (existePessoa.isEmpty()) {
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        return existePessoa.get();
    }
}

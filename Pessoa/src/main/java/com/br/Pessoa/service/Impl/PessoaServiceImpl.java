package com.br.Pessoa.service.Impl;

import com.br.Pessoa.dto.BoletoDTO;
import com.br.Pessoa.entity.Pessoa;
import com.br.Pessoa.exception.PessoaNotFoundException;
import com.br.Pessoa.feign.BoletoFeignClient;
import com.br.Pessoa.repository.PessoaRepository;
import com.br.Pessoa.response.PessoaResponse;
import com.br.Pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;
    private final ModelMapper modelMapper;
    private final BoletoFeignClient boletoFeignClient;

    public PessoaResponse obterBoletosPorIdPessoa(Long idPessoa) {
        Optional<Pessoa> pessoaOptional = repository.findById(idPessoa);

        if (pessoaOptional.isPresent()) {
            Pessoa p = pessoaOptional.get();
            PessoaResponse response = new PessoaResponse();
            response.setId(p.getId());
            response.setNome(p.getNome());
            response.setCpf(p.getCpf());
            response.setEndereco(p.getEndereco());
            response.setDataNascimento(p.getDataNascimento());
            List<BoletoDTO> boletosByPessoaId = boletoFeignClient.getBoletosByPessoaId(idPessoa);
            response.setBoletos(boletosByPessoaId
                    .stream()
                    .map(this::boletoToDTO)
                    .collect(Collectors.toList()));
            return response;
        }
        throw new PessoaNotFoundException();
    }

    public PessoaResponse.Boleto boletoToDTO(BoletoDTO boletoDTO) {
        PessoaResponse.Boleto boleto = new PessoaResponse.Boleto();
        boleto.setIdBoleto(boletoDTO.getIdBoleto());
        boleto.setValor(boletoDTO.getValor());
        boleto.setStatus(boletoDTO.getStatus());
        boleto.setValorPago(boletoDTO.getValorPago());
        boleto.setDataPagamento(boletoDTO.getDataPagamento());
        boleto.setDataVencimento(boletoDTO.getDataVencimento());
        return boleto;
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
            throw new PessoaNotFoundException();
        }
        return existePessoa.get();
    }
}

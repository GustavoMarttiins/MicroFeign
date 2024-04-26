package com.br.Pessoa.controller;

import com.br.Pessoa.entity.Pessoa;
import com.br.Pessoa.response.PessoaResponse;
import com.br.Pessoa.service.Impl.PessoaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
@Tag(name = "Endpoints Pessoa")
public class PessoaController {

    private final PessoaServiceImpl pessoaService;

    @Operation(summary = "Busca boletos por Pessoa")
    @GetMapping("/api/{id}/boletos")
    public PessoaResponse getBoletosByPessoaId(@PathVariable("id") Long id) {
        return pessoaService.obterBoletosPorIdPessoa(id);
    }

    @Operation(summary = "Busca lista de Pessoas")
    @GetMapping
    public List<Pessoa> getAllPessoa() {
        List<Pessoa> pessoa = pessoaService.allPessoas();
        return new ResponseEntity<>(pessoa, HttpStatus.OK).getBody();
    }

    @Operation(summary = "Busca Pessoa por ID")
    @GetMapping("/api/{id}")
    public Optional<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.pessoaById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK).getBody();
    }

    @Operation(summary = "Cadastrar uma nova Pessoa")
    @PostMapping()
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createUsuario(pessoa);
        return new ResponseEntity<>(createdPessoa, HttpStatus.CREATED);
    }

    @Operation(summary = "Alterar dados de uma pessoa")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> updatePessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Optional<Pessoa> updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        return new ResponseEntity<Optional<Pessoa>>(updatedPessoa, HttpStatus.OK);
    }

    @Operation(summary = "Excluir pessoa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.br.Pessoa.controller;

import com.br.Pessoa.entity.Pessoa;
import com.br.Pessoa.service.Impl.PessoaServiceImpl;
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
public class PessoaController {

    private final PessoaServiceImpl pessoaService;

    @GetMapping
    public List<Pessoa> getAllPessoa() {
        List<Pessoa> pessoa = pessoaService.allPessoas();
        return new ResponseEntity<>(pessoa, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.pessoaById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK).getBody();
    }

    @PostMapping("/create")
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createUsuario(pessoa);
        return new ResponseEntity<>(createdPessoa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> updatePessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Optional<Pessoa> updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        return new ResponseEntity<Optional<Pessoa>>(updatedPessoa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

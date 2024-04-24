package com.br.Boleto.controller;

import com.br.Boleto.entity.Boleto;
import com.br.Boleto.service.BoletoService;
import com.br.Boleto.service.Impl.BoletoServiceImpl;
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
public class BoletoController {

    private final BoletoServiceImpl boletoService;

    @GetMapping
    public List<Boleto> getAllBoleto() {
        List<Boleto> boleto = boletoService.allBoleto();
        return new ResponseEntity<>(boleto, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public Optional<Boleto> getBoletoById(@PathVariable Long id) {
        Optional<Boleto> boleto = boletoService.boletoById(id);
        return new ResponseEntity<>(boleto, HttpStatus.OK).getBody();
    }

    @PostMapping("/create")
    public ResponseEntity<Boleto> createBoleto(@Valid @RequestBody Boleto pessoa) {
        Boleto createdBoleto = boletoService.createBoleto(pessoa);
        return new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Boleto>> updateBoleto(@PathVariable Long id, @Valid @RequestBody Boleto pessoa) {
        Optional<Boleto> updatedBoleto = boletoService.updateBoleto(id, pessoa);
        return new ResponseEntity<Optional<Boleto>>(updatedBoleto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
        boletoService.deleteBoleto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

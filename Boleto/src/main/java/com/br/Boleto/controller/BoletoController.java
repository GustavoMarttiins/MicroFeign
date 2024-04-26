package com.br.Boleto.controller;

import com.br.Boleto.dto.BoletoDTO;
import com.br.Boleto.entity.Boleto;
import com.br.Boleto.service.Impl.BoletoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boleto")
@Validated
public class BoletoController {

    private final BoletoServiceImpl boletoService;

    @GetMapping("/api/boletos/{id}")
    public List<BoletoDTO> getBoletosByPessoaId(@PathVariable("id") Long id) {
        return boletoService.getBoletosByPessoaId(id);
    }

    @GetMapping("/{id}")
    public Optional<Boleto> getBoletoById(@PathVariable Long id) {
        return boletoService.boletoById(id);
    }

    @GetMapping()
    public List<Boleto> getBoletoById() {
        return boletoService.allBoleto();
    }
    @PostMapping("/{boletoId}/pagamento")
    public ResponseEntity<Void> realizarPagamento(
            @PathVariable Long boletoId,
            @RequestParam BigDecimal valorPago,
            @RequestParam String dataPagamento) {
        LocalDate dataPagamentoParsed = LocalDate.parse(dataPagamento);
        boletoService.realizarPagamento(boletoId, valorPago, dataPagamentoParsed);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping()
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

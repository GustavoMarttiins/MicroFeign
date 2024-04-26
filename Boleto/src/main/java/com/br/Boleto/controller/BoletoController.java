package com.br.Boleto.controller;

import com.br.Boleto.dto.BoletoDTO;
import com.br.Boleto.entity.Boleto;
import com.br.Boleto.service.Impl.BoletoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Endpoints Boleto")
public class BoletoController {

    private final BoletoServiceImpl boletoService;

    @Operation(summary = "Busca boletos por Pessoa")
    @GetMapping("/api/boletos/{id}")
    public List<BoletoDTO> getBoletosByPessoaId(@PathVariable("id") Long id) {
        return boletoService.getBoletosByPessoaId(id);
    }

    @Operation(summary = "Busca boleto por ID")
    @GetMapping("/{id}")
    public Optional<Boleto> getBoletoById(@PathVariable Long id) {
        return boletoService.boletoById(id);
    }

    @Operation(summary = "Busca lista de boletos")
    @GetMapping()
    public List<Boleto> listaBoleto() {
        return boletoService.allBoleto();
    }

    @Operation(summary = "Realizar pagamento de um Boleto")
    @PostMapping("/{boletoId}/pagamento")
    public ResponseEntity<Void> realizarPagamento(
            @PathVariable Long boletoId,
            @RequestParam BigDecimal valorPago,
            @RequestParam String dataPagamento) {
        LocalDate dataPagamentoParsed = LocalDate.parse(dataPagamento);
        boletoService.realizarPagamento(boletoId, valorPago, dataPagamentoParsed);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Criar um novo Boleto")
    @PostMapping()
    public ResponseEntity<Boleto> createBoleto(@Valid @RequestBody Boleto pessoa) {
        Boleto createdBoleto = boletoService.createBoleto(pessoa);
        return new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
    }

    @Operation(summary = "Alterar dados do Boleto")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Boleto>> updateBoleto(@PathVariable Long id, @Valid @RequestBody Boleto pessoa) {
        Optional<Boleto> updatedBoleto = boletoService.updateBoleto(id, pessoa);
        return new ResponseEntity<Optional<Boleto>>(updatedBoleto, HttpStatus.OK);
    }

    @Operation(summary = "Deletar boleto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
        boletoService.deleteBoleto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

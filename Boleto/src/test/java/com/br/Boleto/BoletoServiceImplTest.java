package com.br.Boleto;

import com.br.Boleto.dto.BoletoDTO;
import com.br.Boleto.entity.Boleto;
import com.br.Boleto.enums.StatusBoleto;
import com.br.Boleto.repository.BoletoRepository;
import com.br.Boleto.service.Impl.BoletoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoletoServiceImplTest {

	@Mock
	private BoletoRepository repository;

	@InjectMocks
	private BoletoServiceImpl boletoService;

	@Test
	public void testGetBoletosByPessoaId() {
		Boleto boleto = new Boleto();
		boleto.setIdBoleto(1L);
		boleto.setValor(new BigDecimal("100.00"));
		boleto.setValorPago(new BigDecimal("100.00"));
		boleto.setDataVencimento(LocalDate.of(2022, 12, 31));
		boleto.setDataPagamento(LocalDate.of(2022, 12, 31));
		boleto.setStatus(StatusBoleto.valueOf("PAGO"));

		when(repository.findAllByIdPessoa(anyLong()))
				.thenReturn(List.of(boleto));

		List<BoletoDTO> boletos = boletoService.getBoletosByPessoaId(1L);
		assertEquals(1, boletos.size());
		BoletoDTO boletoDTO = boletos.get(0);
		assertEquals(boleto.getIdBoleto(), boletoDTO.getIdBoleto());
		assertEquals(boleto.getValor(), boletoDTO.getValor());
		assertEquals(boleto.getValorPago(), boletoDTO.getValorPago());
		assertEquals(boleto.getDataVencimento(), boletoDTO.getDataVencimento());
		assertEquals(boleto.getDataPagamento(), boletoDTO.getDataPagamento());
		assertEquals(boleto.getStatus(), boletoDTO.getStatus());
	}
}

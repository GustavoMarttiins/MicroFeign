package com.br.Pessoa;

import com.br.Pessoa.dto.BoletoDTO;
import com.br.Pessoa.entity.Pessoa;
import com.br.Pessoa.feign.BoletoFeignClient;
import com.br.Pessoa.repository.PessoaRepository;
import com.br.Pessoa.response.PessoaResponse;
import com.br.Pessoa.service.Impl.PessoaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceImplTest extends Utils{

	@Mock
	private PessoaRepository repository;

	@Mock
	private BoletoFeignClient boletoFeignClient;

	@InjectMocks
	private PessoaServiceImpl pessoaService;

	@Test
	public void obterBoletosPorIdPessoaTest() {
		Pessoa pessoa = getPessoa();
		BoletoDTO boleto = getBoleto();
		when(repository.findById(anyLong()))
				.thenReturn(Optional.of(pessoa));
		when(boletoFeignClient.getBoletosByPessoaId(anyLong()))
				.thenReturn(Collections.singletonList(boleto));

		PessoaResponse response = pessoaService.obterBoletosPorIdPessoa(1L);
		assertEquals(pessoa.getId(), response.getId());
		assertEquals(pessoa.getNome(), response.getNome());
		assertEquals(pessoa.getCpf(), response.getCpf());
		assertEquals(pessoa.getEndereco(), response.getEndereco());
		assertEquals(pessoa.getDataNascimento(), response.getDataNascimento());
		assertEquals(1, response.getBoletos().size());

	}

}

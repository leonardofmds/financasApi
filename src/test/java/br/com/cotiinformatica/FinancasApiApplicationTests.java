package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.models.dtos.TipoResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FinancasApiApplicationTests {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	Faker faker = new Faker();

	private static Integer idTipo;
	private static Integer idConta;

	@Test
	@Order(1)
	void cadastrarTipoTest() throws Exception {

		var request = new TipoRequestDto();
		request.setNome("Teste " + faker.commerce().department());

		var result = mockMvc.perform(post("/api/v1/tipo/criar")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);

		assertNotNull(response.getId());
		assertTrue(response.getId() > 0);
		assertEquals(response.getNome(), request.getNome());

		idTipo = response.getId();
	}

	@Test
	@Order(2)
	void atualizarTipoTest() throws Exception {

		var request = new TipoRequestDto();
		request.setNome("Teste " + faker.commerce().department());

		var result = mockMvc.perform(put("/api/v1/tipo/alterar/" + idTipo)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);

		assertEquals(response.getId(), idTipo);
		assertEquals(response.getNome(), request.getNome());
	}

	@Test
	@Order(3)
	void consultarTiposTest() throws Exception {

		var result = mockMvc.perform(get("/api/v1/tipo/consultar"))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue
				(content, new TypeReference<List<TipoResponseDto>>() {});

		response.stream()
				.filter(item -> item.getId() == idTipo)
				.findFirst()
				.orElseThrow(() -> new AssertionError("Tipo não encontrado"));
	}

	@Test
	@Order(4)
	void obterTipoTest() throws Exception {

		var result = mockMvc.perform(get("/api/v1/tipo/obter/" + idTipo))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, TipoResponseDto.class);

		assertNotNull(response);
		assertEquals(response.getId(), idTipo);
	}

	@Test
	@Order(5)
	void excluirTipoTest() throws Exception {

		var result = mockMvc.perform(delete("/api/v1/tipo/excluir/" + idTipo))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, TipoResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(response.getId(), idTipo);
	}

	@Test
	@Order(6)
	void cadastrarContaTest() throws Exception {

		var request = new ContaRequestDto();
		request.setNome(faker.commerce().productName());
		request.setData(new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()));
		request.setValor(faker.number().randomDouble(2, 1, 1000));
		request.setMovimentacao("2");

		var result = mockMvc.perform(post("/api/v1/conta/criar")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response.getId());
		assertTrue(response.getId() > 0);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getData(), request.getData());
		assertEquals(response.getValor(), request.getValor());

		idConta = response.getId();
	}

	@Test
	@Order(7)
	void atualizarContaTest() throws Exception {
		var request = new ContaRequestDto();
		request.setNome(faker.commerce().productName());
		request.setData(new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()));
		request.setValor(faker.number().randomDouble(2, 1, 1000));
		request.setMovimentacao("2");

		var result = mockMvc.perform(put("/api/v1/conta/alterar/" + idConta)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response.getId());
		assertTrue(response.getId() > 0);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getData(), request.getData());
		assertEquals(response.getValor(), request.getValor());

	}

	@Test
	@Order(8)
	void consultarContasTest() throws Exception {

		var result = mockMvc.perform(get("/api/v1/conta/consultar"))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue
				(content, new TypeReference<List<ContaResponseDto>>() {});

		response.stream()
				.filter(item -> item.getId() == idConta)
				.findFirst()
				.orElseThrow(() -> new AssertionError("Conta não encontrado"));
	}

	@Test
	@Order(9)
	void obterContaTest() throws Exception {

		var result = mockMvc.perform(get("/api/v1/conta/obter/" + idConta))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response);
		assertEquals(response.getId(), idConta);
	}

	@Test
	@Order(10)
	void excluirContaTest() throws Exception {

		var result = mockMvc.perform(delete("/api/v1/conta/excluir/" + idConta))
				.andExpect(status().isOk())
				.andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		var response = objectMapper.readValue(content, ContaResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(response.getId(), idConta);
	}
}




package br.com.cotiinformatica.financasApi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;

import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FinancasApiApplicationTests {
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	Faker faker = new Faker();

	@Test
	@Order(1)
	void cadastrarTipoTest() throws Exception {

		//criando os dados para gravação
		var request = new TipoRequestDto();
		request.setNome("Teste " + faker.commerce().department());

		//enviando para a API realizar o cadastro
		var result = mockMvc.perform(post("/api/v1/tipo/criar") //chamada para o endpoint
						.contentType("application/json") //formato json
						.content(objectMapper.writeValueAsString(request))) //dados enviados
				.andExpect(status().isOk()) //verificando a resposta da API
				.andReturn(); //capturando o retorno da API

		//deserializando os dados obtidos
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8); //lendo o JSON retornado pela API
		var response = objectMapper.readValue(content, TipoResponseDto.class); //deserializando o JSON para o DTO

		//asserções de teste (verificações)
		assertNotNull(response.getId()); //ID não pode ser null
		assertTrue(response.getId() > 0); //ID deve ser maior que zero)
		assertEquals(response.getNome(), request.getNome()); //nome deve ser igual ao enviado)
	}
	@Test
	@Order(2)
	void atualizarTipoTest() throws Exception {
		fail("Não implementado.");
	}
	@Test
	@Order(3)
	void consultarTiposTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(4)
	void obterTipoTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(5)
	void excluirTipoTest() throws Exception {
		fail("Não implementado.");
	}
	@Test
	@Order(6)
	void cadastrarContaTest() throws Exception {
		fail("Não implementado.");
	}
	@Test
	@Order(7)
	void atualizarContaTest() throws Exception {
		fail("Não implementado.");
	}
	@Test
	@Order(8)
	void consultarContasTest() throws Exception {
		fail("Não implementado.");
	}
	@Test
	@Order(9)
	void obterContaTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(10)
	void excluirContaTest() throws Exception {
		fail("Não implementado.");
	}
}



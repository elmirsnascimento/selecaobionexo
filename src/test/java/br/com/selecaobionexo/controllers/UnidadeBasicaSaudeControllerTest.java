package br.com.selecaobionexo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*
 * Autor: Elmir da Silva Nascimento
 * Descrição: Classe que possui métodos de teste para validar se serviço rest está OK após mudanças na api.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnidadeBasicaSaudeControllerTest {

	private static final String POSICAO_NOME = "$[0].nome";
	private static final String POSICAO_LATITUDE_UBS_ZERO = "$[0].latitude";
	private static final String POSICAO_LATITUDE_UBS_UM = "$[1].latitude";
	private static final String POSICAO_LATITUDE_UBS_VINTE = "$[20].latitude";
	private static final String POSICAO_ENDERECO = "$[0].endereco";

	private static final String PARAM_ENDERECO_COMPLETO = "enderecoCompleto";
	private static final String PARAM_NOME = "nome";
	private static final String PARAM_LATITUDE = "latitude";
	private static final String PARAM_LONGITUDE = "longitude";
	private static final String PARAM_RAIO = "raio";

	private static final String MOCK_NOME = "UAPSF PROF HETTY ROSA DE MOURA E COSTA";
	private static final String MOCK_ENDERECO = "AVENIDA EZIDIO NEVES";
	private static final String MOCK_ENDERECO_COMPLETO = "AVENIDA EZIDIO NEVES, CERRADO";
	private static final String MOCK_LATITUDE = "-7.59486794471719";
	private static final String MOCK_LONGITUDE = "-72.9278898239115";
	private static final String MOCK_RAIO = "0.5f";

	private static final String MIME_TYPE_TEST = "application/json;charset=UTF-8";

	@Autowired
	private MockMvc mockMvc;

	private HttpHeaders headers;

	@Before
	public void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	}

	@Test
	public void testBuscarUbsEmTodosOsDados() throws Exception {
		mockMvc.perform(post("/buscarUbsEmTodosOsDados").headers(headers).param(PARAM_ENDERECO_COMPLETO, MOCK_ENDERECO))
				.andExpect(status().isOk()).andExpect(content().contentType(MIME_TYPE_TEST))
				.andExpect(jsonPath(POSICAO_LATITUDE_UBS_ZERO).exists())
				.andExpect(jsonPath(POSICAO_NOME).value(MOCK_NOME));
	}

	@Test
	public void testBuscarUbsPorNome() throws Exception {
		mockMvc.perform(post("/buscarUbsPorNome").headers(headers).param(PARAM_NOME, MOCK_NOME))
				.andExpect(status().isOk()).andExpect(content().contentType(MIME_TYPE_TEST))
				.andExpect(jsonPath(POSICAO_LATITUDE_UBS_ZERO).exists())
				.andExpect(jsonPath(POSICAO_ENDERECO).value(MOCK_ENDERECO_COMPLETO));
	}

	@Test
	public void testBuscarUbsDentroDeRaio() throws Exception {
		mockMvc.perform(post("/buscarUbsDentroDeRaio").headers(headers).param(PARAM_LONGITUDE, MOCK_LONGITUDE)
				.param(PARAM_LATITUDE, MOCK_LATITUDE)).andExpect(status().isOk())
				.andExpect(content().contentType(MIME_TYPE_TEST)).andExpect(jsonPath(POSICAO_LATITUDE_UBS_UM).exists())
				.andExpect(jsonPath(POSICAO_LATITUDE_UBS_UM).exists());
	}

	@Test
	public void testBuscarUbsDentroDeRaioEspecifico() throws Exception {
		mockMvc.perform(post("/buscarUbsDentroDeRaioEspecifico").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).param(PARAM_LONGITUDE, MOCK_LONGITUDE)
				.param(PARAM_LATITUDE, MOCK_LATITUDE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MIME_TYPE_TEST))
				.andExpect(jsonPath(POSICAO_LATITUDE_UBS_VINTE).exists());
	}

}

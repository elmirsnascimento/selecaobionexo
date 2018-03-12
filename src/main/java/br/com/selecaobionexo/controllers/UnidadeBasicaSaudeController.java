package br.com.selecaobionexo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.selecaobionexo.common.UnidadeBasicaSaudeRequest;
import br.com.selecaobionexo.common.UnidadeBasicaSaudeResponse;
import br.com.selecaobionexo.converter.UnidadeBasicaSaudeConverter;
import br.com.selecaobionexo.servicos.UnidadeBasicaSaudeService;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Camada Rest acessada pela aplicação Web com métodos que geram respostas Json na estrutura de objetos da UnidadesBasicaSaudeResponse.
*/
@RestController
public class UnidadeBasicaSaudeController {

	@Autowired
	UnidadeBasicaSaudeService ubsService;

	@RequestMapping(value = "/buscarUbsEmTodosOsDados", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UnidadeBasicaSaudeResponse> buscarUbsEmTodosOsDados(@RequestParam String enderecoCompleto) {
		return UnidadeBasicaSaudeConverter.converterUbs(ubsService.buscarUbsEmTodosOsDados(enderecoCompleto));
	}

	@RequestMapping(value = "/buscarUbsPorNome", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UnidadeBasicaSaudeResponse> buscarUbsPorNome(@RequestParam String nome) {
		return UnidadeBasicaSaudeConverter.converterUbs(ubsService.buscarUbsPorNome(nome));
	}

	@RequestMapping(value = "/buscarUbsDentroDeRaio", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UnidadeBasicaSaudeResponse> buscarUbsDentroDeRaio(@RequestBody UnidadeBasicaSaudeRequest ubsRequest) {
		return UnidadeBasicaSaudeConverter
				.converterUbs(ubsService.buscarUbsDentroDeRaio(ubsRequest.getLongitude(), ubsRequest.getLatitude()));
	}

	@RequestMapping(value = "/buscarUbsDentroDeRaioEspecifico", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UnidadeBasicaSaudeResponse> buscarUbsDentroDeRaioEspecifico(@RequestBody UnidadeBasicaSaudeRequest ubsRequest) {
		return UnidadeBasicaSaudeConverter.converterUbs(ubsService.buscarUbsDentroDeRaio(ubsRequest.getLongitude(), ubsRequest.getLatitude(), ubsRequest.getRaio()));
	}

}

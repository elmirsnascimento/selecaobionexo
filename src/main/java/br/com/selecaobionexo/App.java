package br.com.selecaobionexo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.selecaobionexo.servicos.UnidadeBasicaSaudeService;


/*
 * Autor: Elmir da Silva Nascimento
 * Descrição: Classe inicializadora da API
 */
@SpringBootApplication
@EnableMongoRepositories
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	@Autowired
	public void inicializador(UnidadeBasicaSaudeService ubsService) throws Exception {

		ubsService.finalizarColecaoUbs();
		ubsService.inicializarColecaoUbs();

	}

}
package br.com.selecaobionexo.servicos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.selecaobionexo.model.UnidadeBasicaSaude;
import br.com.selecaobionexo.repository.UnidadeBasicaSaudeRepository;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Camada de serviço. Absorve a responsabilidade pelas regras de negócio e chamadas à métodos do repositório da aplicação
*/
@Service
public class UnidadeBasicaSaudeService {
	private final UnidadeBasicaSaudeRepository ubsRepositorio;

	@Autowired
	ObjectMapper jsonMapper;

	@Autowired
	public UnidadeBasicaSaudeService(UnidadeBasicaSaudeRepository ubsRepositorio) {
		this.ubsRepositorio = ubsRepositorio;
	}

	public List<UnidadeBasicaSaude> buscarUbsEmTodosOsDados(String dados) {
		return this.ubsRepositorio.buscarUbsEmTodosOsDados(dados);
	}

	public List<UnidadeBasicaSaude> buscarUbsPorNome(String nome) {
		return this.ubsRepositorio.buscarUbsPorNome(nome);
	}

	public List<UnidadeBasicaSaude> buscarUbsDentroDeRaio(double longitude, double latitude) {
		Circle circle = new Circle(longitude, latitude, 0.03);
		return this.ubsRepositorio.findByLocationWithin(circle);

	}

	public List<UnidadeBasicaSaude> buscarUbsDentroDeRaio(double longitude, double latitude, float raio) {
		Circle circle = new Circle(longitude, latitude, raio);
		return this.ubsRepositorio.findByLocationWithin(circle);
	}

	public void inicializarColecaoUbs() {

		TypeReference<List<UnidadeBasicaSaude>> typeReference = new TypeReference<List<UnidadeBasicaSaude>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/ubs.json");
		try {
			List<UnidadeBasicaSaude> unidades = jsonMapper.readValue(inputStream, typeReference);
			List<UnidadeBasicaSaude> unidadesPersistidas = new ArrayList<>();
			for (UnidadeBasicaSaude unidade : unidades) {
				unidade.setLocation(new GeoJsonPoint(new Point(unidade.getLongitude(), unidade.getLatitude())));
				unidadesPersistidas.add(unidade);
			}
			if (ubsRepositorio.count() <= 0) {
				ubsRepositorio.save(unidadesPersistidas);
			}

			System.out.println("Unidades básicas de saúde inicializadas");
		} catch (IOException e) {
			System.out.println("Não foi possível inicializar unidades básicas de saúde: " + e.getMessage());
		}
	}

	public void finalizarColecaoUbs() {
		ubsRepositorio.deleteAll();
	}

}

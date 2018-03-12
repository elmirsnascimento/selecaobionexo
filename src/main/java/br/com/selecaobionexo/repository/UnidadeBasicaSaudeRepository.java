package br.com.selecaobionexo.repository;

import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.selecaobionexo.model.UnidadeBasicaSaude;


/*
* Autor: Elmir da Silva Nascimento
* Descrição: Repositório. Esta classe herda do repositório mongo e possui métodos de consulta da API.
*/
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface UnidadeBasicaSaudeRepository extends MongoRepository<UnidadeBasicaSaude, String> {

	@Query("{'$or':[ {'endereco':{$regex : ?0}}, {'bairro':{$regex : ?0}}, {'cidade':{$regex : ?0}} ]}")
	List<UnidadeBasicaSaude> buscarUbsEmTodosOsDados(@Param("dados") String dados);

	@Query("{'nome':{$regex : ?0}}")
	List<UnidadeBasicaSaude> buscarUbsPorNome(String nome);

	List<UnidadeBasicaSaude> findByLocationWithin(Circle circle);


}

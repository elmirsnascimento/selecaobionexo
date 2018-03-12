package br.com.selecaobionexo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import br.com.selecaobionexo.servicos.UnidadeBasicaSaudeService;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Configurador de Conversor (necessário para o json poder ser convertido em Documentos através das consultas ao db mongo na camada de serviço e repositorio)
*/
@Configuration
public class MongoConfig {

	private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);

	MongoTemplate mongoTemplate;

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {

		if (mongoTemplate != null) {
			return mongoTemplate;
		}
		try {
			MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),
					context);
			converter.setTypeMapper(new DefaultMongoTypeMapper(null));
			converter.afterPropertiesSet();

			mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
		} catch (Exception e) {
			log.error("Não foi possível inicializar o mongo", e);
			return null;
		}
		return mongoTemplate;

	}

}
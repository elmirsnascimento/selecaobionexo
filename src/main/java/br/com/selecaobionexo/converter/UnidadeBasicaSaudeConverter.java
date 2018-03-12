package br.com.selecaobionexo.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import br.com.selecaobionexo.common.UnidadeBasicaSaudeResponse;
import br.com.selecaobionexo.enums.Avaliacao;
import br.com.selecaobionexo.model.UnidadeBasicaSaude;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Conversor de documentos em respostas à camada Rest.
*/
public class UnidadeBasicaSaudeConverter {

	public static List<UnidadeBasicaSaudeResponse> converterUbs(List<UnidadeBasicaSaude> unidades) {
		List<UnidadeBasicaSaudeResponse> unidadesResponse = new ArrayList<>();

		for (UnidadeBasicaSaude unidade : unidades) {
			UnidadeBasicaSaudeResponse unidadeResponse = new UnidadeBasicaSaudeResponse();
			unidadeResponse.setId(unidade.hashCode());
			unidadeResponse.setLongitude(unidade.getLongitude());
			unidadeResponse.setLatitude(unidade.getLatitude());
			unidadeResponse.setNome(unidade.getNome());
			unidadeResponse.setEndereco(unidade.getEndereco() + ", " + unidade.getBairro());
			if (!StringUtils.isEmpty(unidade.getCidade())) {
				unidadeResponse.setCidade(unidade.getCidade().toUpperCase());
			}
			if (!StringUtils.isEmpty(unidade.getTelefone())) {
				unidadeResponse.setTelefone(unidade.getTelefone().toUpperCase());
			}
			unidadeResponse.setScoreEstruturaFisica(Avaliacao.obterPorDescricao(unidade.getScoreEstruturaFisica()));
			unidadeResponse.setScoreAdaptacao(Avaliacao.obterPorDescricao(unidade.getScoreAdaptacao()));
			unidadeResponse.setScoreEquipamento(Avaliacao.obterPorDescricao(unidade.getScoreEquipamento()));
			unidadeResponse.setScoreMedicamento(Avaliacao.obterPorDescricao(unidade.getScoreMedicamento()));
			unidadesResponse.add(unidadeResponse);
		}

		return unidadesResponse;
	}
}

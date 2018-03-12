package br.com.selecaobionexo.common;

import lombok.Data;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Objeto resposta das requisições Post do serviço rest
*/
@Data
public class UnidadeBasicaSaudeResponse {

	private Integer id;
	private String nome;
	private String endereco;
	private String cidade;
	private String telefone;
	private Double latitude;
	private Double longitude;
	private int scoreEstruturaFisica;
	private int scoreAdaptacao;
	private int scoreEquipamento;
	private int scoreMedicamento;
	
	
	@Override
	public String toString() {
		return "{\n" + 
				"    \"id\": " + this.id + 
				"    ,\"nome\": " + this.nome + 
				"    ,\"endereco\": " + this.endereco +  
				"    ,\"cidade\": " + this.cidade + 
				"    ,\"telefone\": " + this.telefone + 
				"    ,\"latitude\": " + this.latitude +
				"    ,\"longitude\": " + this.longitude +
				"    ,\"scoreEstruturaFisica\": " + this.scoreEstruturaFisica +
				"    ,\"scoreAdaptacao\": " + this.scoreAdaptacao +
				"    ,\"scoreEquipamento\": " + this.scoreEquipamento +
				"    ,\"scoreMedicamento\": " + this.scoreMedicamento +
				"  }";
	}

}

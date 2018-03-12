package br.com.selecaobionexo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Near;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Documento de Unidade Básica de Saúde. Esta classe é utilizada para persistir e obter dados de Ubs.
* É um espelho dos dados do arquivo ubs.json
*/
@Document(collection = "ubs")
@Data
public class UnidadeBasicaSaude {

	@JsonProperty("vlr_latitude")
	private double latitude;
	@JsonProperty("vlr_longitude")
	private double longitude;
	@JsonProperty("cod_munic")
	private Long idMunicipio;
	@JsonProperty("cod_cnes")
	private Long idCnes;
	@JsonProperty("nom_estab")
	private String nome;
	@JsonProperty("dsc_endereco")
	private String endereco;
	@JsonProperty("dsc_bairro")
	private String bairro;
	@JsonProperty("dsc_cidade")
	private String cidade;
	@JsonProperty("dsc_telefone")
	private String telefone;
	@JsonProperty("dsc_estrut_fisic_ambiencia")
	private String scoreEstruturaFisica;
	@JsonProperty("dsc_adap_defic_fisic_idosos")
	private String scoreAdaptacao;
	@JsonProperty("dsc_equipamentos")
	private String scoreEquipamento;
	@JsonProperty("dsc_medicamentos")
	private String scoreMedicamento;
	@JsonProperty("location")
	private GeoJsonPoint location;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeBasicaSaude other = (UnidadeBasicaSaude) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	@Id
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	

}

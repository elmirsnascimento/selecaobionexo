package br.com.selecaobionexo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
* Autor: Elmir da Silva Nascimento
* Descrição: Enum utilizado no processo de conversão de Scores textuais para numéricos.
*/
@AllArgsConstructor
public enum Avaliacao {

	MUITO_ACIMA_MEDIA(3, "Desempenho acima da média"), ACIMA_MEDIA(2,
			"Desempenho mediano ou  um pouco abaixo da média"), MEDIANO(1, "Desempenho muito acima da média");

	@Getter
	@Setter
	private int nota;

	@Getter
	@Setter
	private String descricao;

	public static int obterPorDescricao(String descricao) {
		for (Avaliacao avaliacao : values()) {
			if (avaliacao.descricao.equals(descricao))
				return avaliacao.nota;
		}
		return 0;
	}

}

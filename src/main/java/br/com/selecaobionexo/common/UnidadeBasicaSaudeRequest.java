package br.com.selecaobionexo.common;

import lombok.Data;

@Data
public class UnidadeBasicaSaudeRequest {
	private double longitude;
	private double latitude;
	private float raio;
	
	@Override
	public String toString() {
		return "{ 'longitude' : " + this.longitude + " , 'latitude' : " + this.longitude + " }";
	}
}

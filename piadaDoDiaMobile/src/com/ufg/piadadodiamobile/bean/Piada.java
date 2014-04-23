package com.ufg.piadadodiamobile.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Piada {

	Long id;
	String corpo;
	Long timeDataRecebimento;
	
	public Piada(){}
	
	public Piada(String corpo){
		this.corpo = corpo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public Long getTimeDataRecebimento() {
		return timeDataRecebimento;
	}
	public void setTimeDataRecebimento(Long timeDataRecebimento) {
		this.timeDataRecebimento = timeDataRecebimento;
	}

	@Override
	public String toString() {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(timeDataRecebimento);
		
		return dataFormatada.format(data) + " - " + corpo;
	}
}

package com.ufg.piadaDoDia.envio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaPiada {

	public static void main(String[] args) {

		System.out.println("Insira o local do arquivo de configuração:");

		Scanner scanner = new Scanner(System.in);
		String caminhoArquivo = scanner.nextLine();
		List<String> listaDispositivos = new ArrayList<String>();

		String apiKey = "";
		try {
			FileReader arq = new FileReader(caminhoArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			apiKey = lerArq.readLine();
			System.out.println("APIKEY: " + apiKey);
			String idDispositivo = lerArq.readLine();
			while(idDispositivo != null && !idDispositivo.equals("")){
				listaDispositivos.add(idDispositivo);
				
				idDispositivo = lerArq.readLine();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		System.out.println("Insira a piada que deseja enviar:");
		String piada = scanner.nextLine();

		Sender sender = new Sender(apiKey);
		Message message = new Message.Builder().collapseKey("1").timeToLive(3)
				.delayWhileIdle(true).addData("mensagem", piada).build();

		Result result = null;

		try {
			for(String idDispositivo : listaDispositivos){
				result = sender.send(message, idDispositivo, 1);
				if (result != null) {
					System.out.println("Enviando para o dispositivo: " + idDispositivo + " - Result: " + result.toString());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.ufg.piadaDoDia.envio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaPiada {

	public static void main(String[] args) {

		System.out.println("Insira o local do arquivo de configuração:");

		Scanner scanner = new Scanner(System.in);
		String caminhoArquivo = scanner.nextLine();

		String idDispositivo = "";
		String apiKey = "";
		try {
			FileReader arq = new FileReader(caminhoArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			idDispositivo = lerArq.readLine();
			apiKey = lerArq.readLine();
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
			result = sender.send(message, idDispositivo, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result != null) {
			System.out.println("Id do dispositivo: " + idDispositivo);
			System.out.println("API KEY: " + apiKey);
			System.out.println("Piada: " + piada);
			System.out.println("Resultado: " + result.toString());
		}
	}
}

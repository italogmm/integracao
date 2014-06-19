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
		
		/**
		 * O Arquivo de configuração deve ser um txt com a API KEY na primeira linha, 
		 * e nas demais os ids dos dipositivos paras os quais se quer enviar a mensagem (O Id registrado é mostrado no 
		 * logcat ao executar o app no dispositivo).
		 */
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
			
			lerArq.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		boolean continuar = true;
		while(continuar){
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Enviar piada");
			System.out.println("2 - Sair");
			
			String valorMenu = scanner.nextLine();
			
			if(valorMenu.equals("2"))
				continuar = false;
			else if(valorMenu.equals("1")){
			
				System.out.println("Insira a piada que deseja enviar:");
				String piada = scanner.nextLine();
				
				if(piada != null && !piada.equals("")){
					Sender sender = new Sender(apiKey);
					Message message = new Message.Builder().collapseKey("1").timeToLive(3)
							.delayWhileIdle(true).addData("mensagem", piada).build();
		
					Result result = null;
		
					try {
						for(String idDispositivo : listaDispositivos){
							result = sender.send(message, idDispositivo, 1);
							if (result != null) {
								System.out.println("Piada enviada!");
							}
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

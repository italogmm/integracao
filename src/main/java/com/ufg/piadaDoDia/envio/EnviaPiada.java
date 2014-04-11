package com.ufg.piadaDoDia.envio;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaPiada {

	
	private static final String ID_DISPOSITIVO_GCM = "APA91bFjMlyQKV4vF_e173FAr7yEvztzs5NWtETxLC1cqt3eI6s18tzfkrh0cG9g1-H-NY4uKIWRCI5lQ1KmlJrN1rHdSl-vArpafsPtSMAw6gTmRGmLlXPpVUhOLZvYbIE1n7J1vyw7Av4giV5wT59XWh404x73zxP3yO2MgLxIdWzspYtBvvM";

	// Variável com a chave obtida em API ACCESS no Google APIs
	private static final String API_KEY = "AIzaSyBytpKFCBBxCIHBUmxl5LKNtthYe_ozLxI";

	public static void main(String[] args) {

		/**
		 * ID do Sender (Enviador)
		 */
		Sender sender = new Sender(API_KEY);

		/**
		 * Mensagem a ser enviada
		 */
		Message message = new Message.Builder()
				.collapseKey("1")
				.timeToLive(3)
				.delayWhileIdle(true)
				.addData("mensagem", // identificador da mensagem
						"Olá! Este é um teste de envio de mensagem através do GCM!")
				.build();

		Result result = null;

		/**
		 * Envia a mensagem para o dispositivo
		 */
		try {
			result = sender.send(message, ID_DISPOSITIVO_GCM, 1);
			String canonicalRegId = result.getCanonicalRegistrationId();
			System.out.println("canonicalRegId: " + canonicalRegId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Imprime o resultado do envio na saída padrão
		if (result != null)
			System.out.println(result.toString());

	}
}
